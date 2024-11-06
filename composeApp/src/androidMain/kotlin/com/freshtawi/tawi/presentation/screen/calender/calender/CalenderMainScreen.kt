package com.freshtawi.tawi.presentation.screen.calender.calender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.screen.calender.calender.component.CalenderAddHeader
import com.freshtawi.tawi.presentation.screen.calender.calender.component.CalenderTopBar
import com.freshtawi.tawi.presentation.screen.calender.calender.component.Example1Page


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalenderMainScreen() {
    Scaffold(
        topBar = {
            CalenderTopBar(
                title = "Calender" ,
                initialValue = "",
                onSearchParamChange = {},
                showBackArrow = true,
                showMenuBar = false
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                item {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            verticalArrangement = Arrangement.Center,
                        ) {
                            LocationUi()
                            Example1Page()
                        }

                    }

                }
                item {
                    CalenderActivities()
                }

                items(10){
                    CalenderActivitiesItem()
                }

            }




        }

    }
}

@Composable
@Preview
fun LocationUi() {
        Row(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "CURRENT TIME",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.W400,
                    lineHeight = 15.sp,
                    fontSize = 10.sp
                )
                Text(
                    text = "10:00",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.W700,
                    lineHeight = 30.sp,
                    fontSize = 20.sp
                )
                Text(
                    text = "Wednesday, June 28, 2023",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.W400,
                    lineHeight = 15.sp,
                    fontSize = 10.sp
                )
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "LOCATION",
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.W400,
                    lineHeight = 15.sp,
                    fontSize = 10.sp
                )
                Text(
                    text = "NBO",
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.W700,
                    lineHeight = 30.sp,
                    fontSize = 20.sp
                )
                Text(
                    text = "Nairobi, Kenya",
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.W400,
                    lineHeight = 15.sp,
                    fontSize = 10.sp
                )
            }


        }

}

@Composable
fun CalenderActivities() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Day Overview")
            Text(
                text = "Date Selected",
                fontWeight = FontWeight.W700
            )
        }
    }
    CalenderAddHeader(
        title = "Activities" ,
        numberItems = 4
    )

}

@Composable
fun CalenderActivitiesItem() {
    val expanded = remember { mutableStateOf(false) }
    val icon  = if (expanded.value) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown
    OutlinedCard {
        Column(

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
                Text(text = "Activity Title")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "10: 00 - 10:20")
            }
        }

    }

}