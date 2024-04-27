package com.example.tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.tracker.model.card

@Preview
@Composable
fun ProfileTab(){
    Column(modifier = Modifier.fillMaxSize()){
        TopBar(name = "KAGE #1498")

        Spacer(modifier = Modifier.height(5.dp))

        ProfilePhoto()

        Spacer(modifier = Modifier.height(20.dp))

        FollowButton(modifier = Modifier.fillMaxWidth())
        
        Spacer(modifier = Modifier.height(25.dp))

        RanksHighlight(
            highlight = listOf(
                ImageWithText(
                    image = painterResource(id = R.drawable.plat1),
                    text = "Current Rank"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.plat3),
                    text = "Last Act Rank"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.dia3),
                    text = "Peak Rank"
                )
            )

        )

        Spacer(modifier = Modifier.height(25.dp))
        Agent()
        Spacer(modifier = Modifier.height(25.dp))

        AgentsTab(highlight = listOf(
            ImageWithText(
                image = painterResource(id = R.drawable.breach),
                text = "Breach"
            ),
            ImageWithText(
                image = painterResource(id = R.drawable.cypher),
                text = "Cypher"
            ),
            ImageWithText(
                image = painterResource(id = R.drawable.omen),
                text = "Omen"
            ),
            ImageWithText(
                image = painterResource(id = R.drawable.phoenix),
                text = "Phoenix"
            ),
        )
        )


        Spacer(modifier = Modifier.height(30.dp))
        RecentMatchBar()
        Spacer(modifier = Modifier.height(25.dp))
        RecentMatches()



    }
}


//THIS FUNCTION CONSISTS OF THE ICONS AND USER ID AT THE TOP BAR
@Composable

fun TopBar(name: String){

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth())
    {
        Icon(imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile",
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )

        Text(text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            fontSize = 25.sp)

        Icon(imageVector = Icons.Default.Notifications,
            contentDescription = "Notify",
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )
        Icon(imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )

    }
}

//THE FUNCTION OF THIS CODE IS TO PROVIDE A COLUMN THAT WILL CONSIST OF THE PROFILE IMAGE
//AS WELL AS THE PROFILE STATS AND WILL ALIGN THEM ACCORDINGLY

@Composable
fun ProfilePhoto(
    modifier: Modifier = Modifier
)
{
    Column (modifier = modifier.fillMaxWidth()){
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)){
            ProfileImage(image = painterResource(id = R.drawable.pandhesal),modifier = Modifier
                .size(100.dp)
                .weight(weight = 3f)
            )

            Spacer(modifier = Modifier.width(60.dp))


            StatsPart(modifier = Modifier.weight(7f))


        }
    }
}

//THE DESIGN OF THE PROFILE IMAGE TO ENSURE THAT THE IMAGE WILL BE CIRCLE AND IT'S BORDERS

@Composable
fun ProfileImage(
    image:Painter,
    modifier: Modifier = Modifier
){
    Image(painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

//THE FUNCTION FOR GAMES, FOLLOWERS, AND ACE WHEREIN THE PROFILE STATS IS CALLED

@Composable
fun StatsPart(modifier: Modifier = Modifier){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier)
    {
        
        ProfileStats(numberText = "62", text = "Games")
        ProfileStats(numberText = "10", text = "Followers")
        ProfileStats(numberText = "20", text = "Ace")
    }
}

//THE COLUMN DESIGN FOR GAMES, FOLLOWERS, AND ACE

@Composable
fun ProfileStats(numberText : String, text: String, modifier: Modifier = Modifier){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier)
    {
        Text(text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))


        Text(text = text)

    }
}


//THIS IS WHERE THE DESIGN OF THE ACTION BUTTON ROW RESIDES
@Composable
fun FollowButton(modifier: Modifier = Modifier){
    val minWidth = 95.dp
    val height  = 30.dp

    Row (horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier){
        ActionButton(
            text = "Follow",
            icon = Icons.Default.Add,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Message",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Team-Up",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )

        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .size(height)
        )


    }
}

// THE DESIGN OF BUTTONS SUCH AS FOLLOW, MESSAGE, TEAM-UP
@Composable
fun ActionButton(modifier: Modifier = Modifier,
                  text: String? = null,
                 icon: ImageVector? = null){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ){
        if(text != null){
            Text(text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp)
        }

        if(icon != null){
            Icon(imageVector = icon,
                contentDescription = null,
                tint = Color.Black)
        }

    }
}

//THE RANK DESIGN HIGHLIGHT
@Composable
fun RanksHighlight(modifier: Modifier = Modifier,
                   highlight: List<ImageWithText>
) {
    LazyRow(modifier = modifier) {
        items(highlight.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .padding(start = 20.dp)
            ) {
                ProfileImage(
                    image = highlight[it].image,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    highlight[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

            }
        }
    }

}

//CONTAINS THE DESIGN OF THE AGENTS TAB INCLUDING SHAPE
@Composable
fun AgentsTab(modifier: Modifier = Modifier,
                   highlight: List<ImageWithText>
) {

    LazyRow(modifier = modifier) {
        items(highlight.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .padding(start = 15.dp)
            ) {
                ProfileImage(
                    image = highlight[it].image,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    highlight[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

            }
        }
    }

}

//SCROLLABLE LIST FOR RECENT MATCHES
//CAN BE FURTHER IMPROVED TO BE CLICKABLE
@Composable
fun RecentMatches(

) {

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {

        for (i in 1..50) {
            Text(
                text = "Match $i",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }

    }

}

@Composable
fun Agent(modifier: Modifier = Modifier.fillMaxWidth()){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier) {


        Text(text = "TOP AGENTS",
            fontWeight = FontWeight.Bold
        )
    }
}
//ROW FOR RECENT MATCHES
@Composable
fun RecentMatchBar(modifier: Modifier = Modifier.fillMaxWidth()){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier) {


        Text(text = "RECENT MATCHES",
            fontWeight = FontWeight.Bold
        )
    }
}

//ALL CODES BELOW THIS IS FOR FUTURE PROOFING
    /*
    @Composable
    fun Tabs(
        modifier: Modifier = Modifier,
        imageWithTexts: List<ImageWithText>,
        onTabSelected: (selectedIndex: Int) -> Unit
    ) {

        var selectedTabIndex by remember {
            mutableStateOf(0)
        }

        val inactiveTabColor = Color(0xFF777777)

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.background(Color.Transparent),
        )
        {
            imageWithTexts.forEachIndexed{ index, item ->
                Tab(selected = selectedTabIndex == index,
                    selectedContentColor = Color.Black,
                    unselectedContentColor = inactiveTabColor,
                    onClick = {
                        selectedTabIndex = index
                        onTabSelected(index)

                    }) {

                    Icon(
                        painter = painterResource(id = R.drawable.overview),
                        contentDescription = item.text,
                        tint = if(selectedTabIndex == index )Color.Black else inactiveTabColor,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                    )
                }
            }
        }
        }
   }
*/ //FOR IMPROVEMENTS
/*
@Composable
fun TabView(
    modifier: Modifier = Modifier
){
    Row (horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier){

        Text(text = "Overview",
            fontWeight = FontWeight.ExtraBold
        )
        Text(text = "Matches",
            fontWeight = FontWeight.ExtraBold
        )
        Text(text = "Performances",
            fontWeight = FontWeight.ExtraBold
        )
        Text(text = "Agents",
            fontWeight = FontWeight.ExtraBold
        )
        Text(text = "Maps",
            fontWeight = FontWeight.ExtraBold
            )
        
    }
    
}*/