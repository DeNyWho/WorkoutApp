package com.example.workoutapp.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.workoutapp.domain.model.OnBoardingPage
import com.example.workoutapp.navigation.Screen
import com.example.workoutapp.ui.theme.activeIndicatorColor
import com.example.workoutapp.ui.theme.inactiveIndicatorColor
import com.example.workoutapp.util.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState(pages.size)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.1f),
            count = ON_BOARDING_PAGE_COUNT
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()){
                BodySection(
                    navController = navController,
                    welcomeViewModel =  welcomeViewModel,
                    pagerState = pagerState,
                    onBoardingPage = pages[it])
            }
        }
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BodySection(
    onBoardingPage: OnBoardingPage,
    pagerState: PagerState,
    welcomeViewModel: WelcomeViewModel,
    navController: NavHostController
) {

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "PagerImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 0.dp, 80.dp)
        )
        TextButton(
            onClick = {
                welcomeViewModel.saveOnBoardingState(completed = true)
                navController.navigate(Screen.Home.route)
                      },
            modifier = Modifier.align(Alignment.TopEnd)
//            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = "Skip", color = Color.White)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .fillMaxHeight(0.25f)
                .background(Color.White, RoundedCornerShape(0.dp, 90.dp, 0.dp, 0.dp)),
//            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
                activeColor = MaterialTheme.colors.activeIndicatorColor,
                modifier = Modifier
                    .padding(26.dp)
                    .align(Alignment.BottomStart)
            )


            Text(
                text = onBoardingPage.title,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(16.dp, 20.dp, 0.dp, 0.dp)
                    .align(Alignment.TopStart),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = onBoardingPage.description,
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(16.dp, 50.dp, 0.dp, 0.dp)
                    .align(Alignment.TopStart),
                textAlign = TextAlign.Left
            )

            BottomSection(
                size = pages.size,
                index = pagerState.currentPage,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.BottomEnd)
            ) {
                if (pagerState.currentPage + 1 < pages.size) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomSection(
    modifier: Modifier,
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Box(
        modifier = modifier.padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        val buttonText = if (size == index + 1) "start" else "next"
        
        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text(text = buttonText)
        }
    }
}
