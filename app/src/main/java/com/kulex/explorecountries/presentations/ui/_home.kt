package com.kulex.explorecountries.presentations.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.kulex.explorecountries.presentations.vms.CountryVM

@Composable
fun Home() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val vm = hiltViewModel<CountryVM>()
        LaunchedEffect(key1 = Unit) {
            vm.getCountries()
        }
        val uiState = vm.uiState.collectAsState().value
        Log.e("TAG", "Home: $uiState")
        val (list, loader) = createRefs()

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(loader) {
                    centerTo(parent)
                }
            )
        }

        if (uiState.isSuccess) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.constrainAs(list) {
                    centerTo(parent)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }) {
                items(uiState.dataList) { item ->
                    CountryItem(
                        name = "Name ${item}",
                        capital = "Capital ${item}",
                    )
                }
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryItem(
    name: String = "",
    capital: String = "",
    imgUrl: String = "",
    onClick: () -> Unit = {}
) {

    ElevatedCard(
        onClick = onClick, colors = CardDefaults.elevatedCardColors(
            containerColor = Color.Transparent,
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val (img, nme, captal) = createRefs()

            Text(text = name, style = typography.titleMedium, modifier = Modifier.constrainAs(nme) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
            Text(text = capital, modifier = Modifier.constrainAs(captal) {
                top.linkTo(nme.bottom)
                start.linkTo(nme.start)
            }
            )

        }
    }

}