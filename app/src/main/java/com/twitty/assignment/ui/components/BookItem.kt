package com.twitty.assignment.ui.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.twitty.assignment.R
import com.twitty.assignment.model.Book
import com.twitty.assignment.ui.theme.AppIcons

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ExpandableBookItem(
    book: Book,
    onClickIcon: (Book) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    var expanded by rememberSaveable { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                if (!expanded) {
                    expanded = true
                }
            }
    ) {
        val (image, text, icon) = createRefs()

        GlideImage(
            model = book.imageUrl,
            contentDescription = stringResource(R.string.description_book_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .size(64.dp)
                .padding(8.dp)
        )

        Column(
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(text) {
                    centerVerticallyTo(parent)
                    start.linkTo(image.end)
                    end.linkTo(icon.start)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(
                text = book.title,
                softWrap = expanded,
                overflow = if (expanded) TextOverflow.Clip else TextOverflow.Ellipsis,
            )
            Text(
                text = book.link,
                softWrap = expanded,
                overflow = if (expanded) TextOverflow.Clip else TextOverflow.Ellipsis,
                modifier = if (expanded) {
                    Modifier.clickable {
                        if (Uri.parse(book.link).scheme != null) { // Uri 유효성 확인
                            uriHandler.openUri(book.link)
                        }
                    }
                } else Modifier
            )
        }

        Icon(
            imageVector = if (book.isFavorites) AppIcons.Favorite else AppIcons.FavoriteBorder,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { onClickIcon(book) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExpandableBookItem() {
    ExpandableBookItem(
        book = Book(
            title = "Android Studio를 활용한 안드로이드 프로그래밍 (Android 12.0(S) 지원)",
            link = "https://search.shopping.naver.com/book/catalog/32436264666",
            imageUrl = "https://shopping-phinf.pstatic.net/main_3243626/32436264666.20221019104929.jpg",
            isFavorites = false
        ),
        onClickIcon = {}
    )
}