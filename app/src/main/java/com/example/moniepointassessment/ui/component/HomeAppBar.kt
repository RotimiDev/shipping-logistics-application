package com.example.moniepointassessment.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moniepointassessment.R

@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    showSearchResultsOnly: Boolean = false,
    onReadyToSearch: (Boolean) -> Unit = {},
    onSearchQueryChanged: (String) -> Unit = {}
) {
    Box(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.purple_light))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AnimatedVisibility(visible = !showSearchResultsOnly, modifier = modifier) {
                ProfileView()
            }
            Spacer(Modifier.height(20.dp))
            SearchView(
                searchQuery = searchQuery,
                showSearchResultsOnly = showSearchResultsOnly,
                onQueryCleared = { onSearchQueryChanged("") },
                onReadyToSearch = onReadyToSearch,
                onSearchQueryChanged = onSearchQueryChanged
            )
        }
    }
}

@Composable
private fun ProfileView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(38.dp),
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal, fontSize = 12.sp),
                        text = stringResource(id = R.string.your_location),
                        color = Color(0xFFF8F8F8)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = stringResource(id = R.string.location_name),
                        color = Color.White
                    )
                    Image(
                        painter = painterResource(id = R.drawable.white_down_arrow),
                        modifier = Modifier.size(16.dp),
                        contentDescription = ""
                    )
                }
            }
        }

        Image(
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .size(38.dp)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_notifications),
            contentDescription = ""
        )
    }
}

@Composable
private fun SearchView(
    searchQuery: String,
    showSearchResultsOnly: Boolean = false,
    onQueryCleared: () -> Unit = {},
    onReadyToSearch: (Boolean) -> Unit = {},
    onSearchQueryChanged: (String) -> Unit = {}
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

        AnimatedVisibility(visible = showSearchResultsOnly) {
            Image(
                modifier = Modifier
                    .size(34.dp)
                    .padding(horizontal = 8.dp)
                    .clickable {
                        onQueryCleared()
                        onReadyToSearch(false)
                    },
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = ""
            )
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(id = R.color.very_light_brown))
                .fillMaxWidth()
                .padding(start = 16.dp, top = 6.dp, bottom = 6.dp, end = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Image(
                    modifier = Modifier.size(14.dp),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.purple_light)),
                    painter = painterResource(R.drawable.magnifying),
                    contentDescription = ""
                )

                Spacer(Modifier.width(16.dp))

                BasicTextField(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(FocusRequester.Default)
                        .onFocusChanged { onReadyToSearch(it.isFocused) },
                    value = searchQuery,
                    onValueChange = { newValue ->
                        onSearchQueryChanged(newValue)
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchQueryChanged(searchQuery)
                        }
                    ),
                    decorationBox = { innerTextField ->
                        Box {
                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.search_text),
                                    color = colorResource(id = R.color.brown),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            Image(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.orange))
                    .size(32.dp)
                    .padding(6.dp),
                painter = painterResource(id = R.drawable.ic_card),
                contentDescription = ""
            )
        }
    }
}
