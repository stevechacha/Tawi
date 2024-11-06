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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.freshtawi.tawi.presentation.common.resources.util.compressImage
import com.freshtawi.tawi.presentation.common.resources.util.createImageFile
import com.freshtawi.tawi.presentation.common.resources.util.imageUriToImageBitmap
import com.freshtawi.tawi.presentation.common.resources.util.saveImage
import com.mr0xf00.easycrop.CropError
import com.mr0xf00.easycrop.CropResult
import com.mr0xf00.easycrop.crop
import com.mr0xf00.easycrop.rememberImageCropper
import com.mr0xf00.easycrop.ui.ImageCropperDialog
import com.freshtawi.tawi.presentation.common.composable.AppToolbar
import kotlinx.coroutines.launch
import java.io.File
import androidx.navigation.NavController
import com.freshtawi.tawi.presentation.common.resources.Resources


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductPhotoScreen(
    viewModel: AddProductsViewModel,
    navController: NavController,
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
                                Toast.makeText(context, "CropError.LoadingError", Toast.LENGTH_SHORT).show()
                            }
                            CropError.SavingError -> {
                                Toast.makeText(context, "CropError.SavingError", Toast.LENGTH_SHORT).show()
                            }
                            CropResult.Cancelled -> {
                                Toast.makeText(context, "CropResult.Cancelled", Toast.LENGTH_SHORT).show()
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

    Scaffold(
        topBar = {
            AppToolbar(
                onNavigateBack = {},
                title = Resources.strings.photos,
                showBackArrow = true,
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            
            item {
                Box {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.clickable(MutableInteractionSource(), null) {
                            galleryLauncher.launch("image/*")
                        }
                    ) {
                        ConstraintLayout(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val (text, icon, divider) = createRefs()

                            Text(
                                text = "Add Photos",
                                modifier = Modifier.constrainAs(text) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    bottom.linkTo(icon.bottom)
                                }
                            )

                            IconButton(
                                onClick = { /*TODO*/ },
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = MaterialTheme.colorScheme.onBackground
                                ),
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Back",
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                modifier = Modifier.constrainAs(icon) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                }
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .constrainAs(divider) {
                                        top.linkTo(icon.bottom)
                                    },
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
            
            
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .height(200.dp)
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
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (viewModel.productImageUri.value == null) {
                            Toast.makeText(
                                context,
                                "Please select an image so as to proceed",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                    },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = "Next",
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}



