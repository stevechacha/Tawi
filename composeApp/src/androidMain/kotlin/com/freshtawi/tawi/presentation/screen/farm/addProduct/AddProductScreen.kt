package com.freshtawi.tawi.presentation.screen.farm.addProduct

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mr0xf00.easycrop.CropError
import com.mr0xf00.easycrop.CropResult
import com.mr0xf00.easycrop.crop
import com.mr0xf00.easycrop.rememberImageCropper
import com.mr0xf00.easycrop.ui.ImageCropperDialog
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.composable.VnExpandableTextField
import com.freshtawi.tawi.presentation.common.composable.AppToolbar
import com.freshtawi.tawi.presentation.common.composable.VunaTecDropDown
import com.freshtawi.tawi.presentation.common.composable.getCounties
import com.freshtawi.tawi.presentation.common.resources.util.compressImage
import com.freshtawi.tawi.presentation.common.resources.util.createImageFile
import com.freshtawi.tawi.presentation.common.resources.util.imageUriToImageBitmap
import com.freshtawi.tawi.presentation.common.resources.util.saveImage
import com.freshtawi.tawi.presentation.screen.farm.addProduct.state.AddProductEvent
import com.freshtawi.tawi.presentation.screen.farm.FarmNavigationScreen
import com.freshtawi.tawi.presentation.screen.farm.components.AddPhotoComponent
import com.freshtawi.tawi.presentation.common.resources.Resources
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun AddProductScreen(
    addProductsViewModel: AddProductsViewModel = viewModel(),
    navController: NavController
) {
    val addProductState by addProductsViewModel.state.collectAsState()
    Scaffold(
        topBar = {
            AppToolbar(
                onNavigateBack = {},
                title = Resources.strings.addProductTitle,
                showBackArrow = true
            )
        },
        bottomBar = {

                AppButton(
                    text = Resources.strings.addButtonText,
                    onClick = {
                        navController.navigate(FarmNavigationScreen.AddPesticide.route)
                    }
                )

        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AppOutlinedTextField(
                value = addProductState.productName,
                onValueChange = {
                    addProductsViewModel.onEvent(AddProductEvent.OnProductNameChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = Resources.strings.productName,
                hint = Resources.strings.productNameLabelHint,
            )

            VnExpandableTextField(
                value = addProductState.productDescription,
                onValueChange = {
                    addProductsViewModel.onEvent(AddProductEvent.OnProductDescriptionChanged(it))
                },
                label = Resources.strings.productDescriptionLabel,
                hint = Resources.strings.productDescriptionHint,
            )
            VunaTecDropDown(
                value = addProductState.productType,
                onValueChanged = {
                    addProductsViewModel.onEvent(AddProductEvent.OnProductTypeChanged(it))
                },
                label = Resources.strings.productTypeVarietyLabel,
                hint = Resources.strings.productTypeVarietyHint,
                data = getCounties(),
                showLabel = true
            )

            AppOutlinedTextField(
                value = addProductState.noOfTrees.toString(),
                onValueChange = {
                    addProductsViewModel.onEvent(AddProductEvent.OnNoOfTreesChange(it))
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number,
                label = Resources.strings.productQuantityLabel,
                hint = Resources.strings.productQuantityHint,

                )

            Spacer(modifier = Modifier.height(12.dp))
            AddPhotos(
                viewModel = addProductsViewModel,
                navController = navController
            )

        }
    }

}

@Composable
fun AddPhotos(
    viewModel: AddProductsViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val imageCropper = rememberImageCropper()
    val scope = rememberCoroutineScope()
    var imageUri by remember { mutableStateOf<File?>(null) }
    var compressedImageUri by remember { mutableStateOf<Uri?>(null) }

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { granted ->
                hasCamPermission = granted
            }
        )

    val photoLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                if (success) {
                    if (imageUri != null) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            context.contentResolver,
                            imageUri!!.toUri()
                        )
                        val compressedBitmap = compressImage(bitmap)
                        compressedImageUri = saveImage(context, compressedBitmap)

                        scope.launch {
                            when (imageCropper.crop(uri = imageUri!!.toUri(), context = context)) {
                                CropError.LoadingError -> {
                                    Toast.makeText(
                                        context,
                                        "CropError.LoadingError",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                                CropError.SavingError -> {
                                    Toast.makeText(
                                        context,
                                        "CropError.SavingError",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                                CropResult.Cancelled -> {
                                    Toast.makeText(
                                        context,
                                        "CropResult.Cancelled",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                                is CropResult.Success -> {
                                    viewModel.setProductImageUri(
                                        compressedImageUri
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
            if (uris.isNotEmpty()) {
                // Process the list of selected URIs
                for (uri in uris) {
                    scope.launch {
                        when (val result = imageCropper.crop(uri = uri, context = context)) {
                            CropError.LoadingError -> {
                                Toast.makeText(
                                    context,
                                    "CropError.LoadingError",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            CropError.SavingError -> {
                                Toast.makeText(context, "CropError.SavingError", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            CropResult.Cancelled -> {
                                Toast.makeText(context, "CropResult.Cancelled", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is CropResult.Success -> {
                                viewModel.setProductImageUri(
                                    saveImage(
                                        context = context,
                                        bitmap = result.bitmap.asAndroidBitmap()
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }


    val singleGalleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                scope.launch {
                    when (val result = imageCropper.crop(uri = uri, context = context)) {
                        CropError.LoadingError -> {
                            Toast.makeText(context, "CropError.LoadingError", Toast.LENGTH_SHORT)
                                .show()
                        }

                        CropError.SavingError -> {
                            Toast.makeText(context, "CropError.SavingError", Toast.LENGTH_SHORT)
                                .show()
                        }

                        CropResult.Cancelled -> {
                            Toast.makeText(context, "CropResult.Cancelled", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is CropResult.Success -> {
                            viewModel.setProductImageUri(
                                saveImage(
                                    context = context,
                                    bitmap = result.bitmap.asAndroidBitmap()
                                )
                            )
                        }
                    }
                }
            }
        }


    LaunchedEffect(key1 = true, block = {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    })

    val cropState = imageCropper.cropState
    if (cropState != null) ImageCropperDialog(state = cropState)

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        AddPhotoComponent(
            onAddClick = {
                galleryLauncher.launch("image/*")
            },
            text = Resources.strings.photos
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .height(100.dp)
                            .clickable {
                                val photoFile = createImageFile(context)

                                if (photoFile != null) {
                                    val photoURI = FileProvider.getUriForFile(
                                        context,
                                        context.applicationContext.packageName + ".fileprovider",
                                        photoFile
                                    )
                                    imageUri = photoFile
                                    photoLauncher.launch(photoURI)
                                }
                            }
                    ) {
                        if (viewModel.productImageUri.value == null) {
                            IconButton(onClick = {
                                val photoFile = createImageFile(context)

                                if (photoFile != null) {
                                    val photoURI = FileProvider.getUriForFile(
                                        context,
                                        context.applicationContext.packageName + ".fileprovider",
                                        photoFile
                                    )
                                    imageUri = photoFile
                                    photoLauncher.launch(photoURI)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Selected Image
                        viewModel.productImageUri.value?.let { uri ->
                            Image(
                                modifier = Modifier
                                    .fillMaxSize(),
                                bitmap = context.imageUriToImageBitmap(uri).asImageBitmap(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .height(100.dp)
                            .clickable {
                                val photoFile = createImageFile(context)

                                if (photoFile != null) {
                                    val photoURI = FileProvider.getUriForFile(
                                        context,
                                        context.applicationContext.packageName + ".fileprovider",
                                        photoFile
                                    )
                                    imageUri = photoFile
                                    photoLauncher.launch(photoURI)
                                }
                            }
                    ) {
                        if (viewModel.productImageUri.value == null) {
                            IconButton(onClick = {
                                val photoFile = createImageFile(context)

                                if (photoFile != null) {
                                    val photoURI = FileProvider.getUriForFile(
                                        context,
                                        context.applicationContext.packageName + ".fileprovider",
                                        photoFile
                                    )
                                    imageUri = photoFile
                                    photoLauncher.launch(photoURI)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Selected Image
                        viewModel.productImageUri.value?.let { uri ->
                            Image(
                                modifier = Modifier
                                    .fillMaxSize(),
                                bitmap = context.imageUriToImageBitmap(uri).asImageBitmap(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

            }
        }

    }


}

