package com.example.tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tracker.model.card

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "profile") {
        composable("profile") { ProfileTab(navController = navController) }
        composable("agents") { AgentsScreen() }
        composable("search") { SearchScreen() }
    }
}

@Composable
fun ProfileTab(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(name = "KAGE #1498", navController = navController)

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

        AgentsTab(
            highlight = listOf(
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

@Composable
fun TopBar(name: String, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile",
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )

        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            fontSize = 25.sp
        )

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notify",
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )

        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier
                .size(40.dp)
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Agents") },
                onClick = {
                    navController.navigate("agents")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Exit") },
                onClick = {
                    // Handle exit action
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun ProfilePhoto(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            ProfileImage(
                image = painterResource(id = R.drawable.pandhesal),
                modifier = Modifier
                    .size(100.dp)
                    .weight(weight = 3f)
            )

            Spacer(modifier = Modifier.width(60.dp))

            StatsPart(modifier = Modifier.weight(7f))
        }
    }
}

@Composable
fun ProfileImage(image: Painter, modifier: Modifier = Modifier) {
    Image(
        painter = image,
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

@Composable
fun StatsPart(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStats(numberText = "62", text = "Games")
        ProfileStats(numberText = "10", text = "Followers")
        ProfileStats(numberText = "20", text = "Ace")
    }
}

@Composable
fun ProfileStats(numberText: String, text: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = text)
    }
}

@Composable
fun FollowButton(modifier: Modifier = Modifier) {
    val minWidth = 95.dp
    val height = 30.dp

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
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
            modifier = Modifier.size(height)
        )
    }
}

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String? = null, icon: ImageVector? = null) {
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
    ) {
        if (text != null) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun RanksHighlight(
    modifier: Modifier = Modifier,
    highlight: List<ImageWithText>
) {
    LazyRow(modifier = modifier) {
        items(highlight.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Image(
                    painter = highlight[it].image,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = highlight[it].text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun Agent() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Top Agents",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun AgentsTab(
    highlight: List<ImageWithText>
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)) {
        highlight.forEach {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = it.image,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = it.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun RecentMatchBar(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Recent Matches",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun RecentMatches() {
    var expandedMatchIndex by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        repeat(5) { matchIndex ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        expandedMatchIndex = if (expandedMatchIndex == matchIndex) -1 else matchIndex
                    }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Match ${matchIndex + 1}", fontWeight = FontWeight.Bold)
                        Icon(
                            imageVector = if (expandedMatchIndex == matchIndex) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                    if (expandedMatchIndex == matchIndex) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Match details go here...")
                    }
                }
            }
        }
    }
}

@Composable
fun AgentsScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "All Agents",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val agents = listOf(
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
            // Add more agents here
        )

        LazyColumn {
            items(agents.size) { index ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = agents[index].image,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = agents[index].text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Search Friends",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search by name or ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Here you can add a logic to display search results from the database.
        // This is a placeholder for the search results
        Text("Search results for \"$searchText\":")
        // Add the search results here...
    }
}
