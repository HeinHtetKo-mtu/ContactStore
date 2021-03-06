package com.mtu.ceit.hhk.contactstore.features.contactlist

import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexstyl.contactstore.Contact
import com.mtu.ceit.hhk.contactstore.R

import com.mtu.ceit.hhk.contactstore.ui.theme.Primary
import com.mtu.ceit.hhk.contactstore.ui.theme.RedVariant

import kotlin.math.roundToInt


@ExperimentalMaterialApi
@Composable
fun ContactListItem(item: Contact,
                    isSelecting:Boolean,
                    contactList:List<Contact>,
                    onItemClick:(Contact)->Unit) {

    val swSt = rememberSwipeableState(initialValue = 0)
    val isSelected = contactList.contains(item)


    val checkedColor = if(isSelecting && isSelected) Color.Green else Color.Transparent
    val surfaceColor = if(isSelecting && isSelected) Primary else MaterialTheme.colors.background


    val siz = with(LocalDensity.current){
        100.dp.toPx()
    }
    Box (modifier = Modifier

        .fillMaxWidth()
        .swipeable(
            state = swSt,
            orientation = Orientation.Horizontal,
            thresholds = { _, _ ->
                FractionalThreshold(0.3f)
            }, anchors = mapOf(0f to 0, siz to 1, -siz to 2)
        )
        .clickable {
            onItemClick.invoke(item)
        }
        .height(100.dp)
        .padding(20.dp, 5.dp)
        .background(RedVariant)
        ,
        contentAlignment = Alignment.CenterStart
        ){

        Row {
            
            Spacer(modifier = Modifier.width(30.dp))
            
            Image(
                painter = painterResource(id = R.drawable.call)
                , modifier = Modifier

                    .size(50.dp)
                    .clickable {

                    }
                , contentDescription = "call icon")
        }

    

        Row(
            Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth()
                .fillMaxHeight()
                .offset { IntOffset(swSt.offset.value.roundToInt(), 0) }
                .background(surfaceColor)
        , verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
            Text(
                text = item.displayName ?: " ",
                fontSize = 18.sp,
                maxLines = 1
                ,fontFamily = FontFamily(Font(R.font.mukutamedium)),
                modifier = Modifier.width(200.dp))

            Icon(painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
             tint = checkedColor)

        }


    }

}

