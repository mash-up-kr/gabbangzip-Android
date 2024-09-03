package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicPhotoCardFrame
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTag
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame

@Composable
fun ThumbnailCardFrame(
    modifier: Modifier = Modifier,
    groupName: String,
    keyword: GroupKeyword,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        PicPhotoCardFrame(
            modifier = Modifier.fillMaxSize(),
            keywordType = keyword,
            frameResId = PicPhotoFrame.getTypeByKeyword(keyword.name).frameResId,
        ) {
            content()
        }
        PicTag(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 25.dp)
                .align(Alignment.TopCenter)
                .background(color = Gray80)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            text = stringResource(id = keyword.tagNameResId),
            iconRes = keyword.symbolResId,
            iconColor = keyword.symbolColor,
            textColor = Gray0,
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 46.dp)
                .align(Alignment.BottomCenter),
            text = groupName,
            style = PicTypography.headBold20,
            color = Gray80,
        )
    }
}
