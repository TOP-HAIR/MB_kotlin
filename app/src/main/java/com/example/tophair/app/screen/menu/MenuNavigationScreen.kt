package com.example.tophair.app.screen.menu

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tophair.app.data.entities.enum.NavMenuEnum
import com.example.tophair.app.data.service.SessionManager
import com.example.tophair.app.data.viewmodel.AgendaViewModel
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.data.viewmodel.ServicoViewModel
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.screen.menu.tabs.CalendarComponent
import com.example.tophair.app.screen.menu.tabs.EmpresaComponent
import com.example.tophair.app.screen.menu.tabs.HomeComponent
import com.example.tophair.app.screen.menu.tabs.SearchComponent
import com.example.tophair.app.screen.menu.tabs.UserComponent
import com.example.tophair.app.utils.HideSystemBars
import com.example.tophair.ui.theme.TopHairTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MenuNavigationView : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val agendaViewModel: AgendaViewModel by viewModels()
    private val empresaViewModel: EmpresaViewModel by viewModels()
    private val servicoViewModel: ServicoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TopHairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val navController = rememberNavController()
//                    val savedStateHandle = remember { SavedStateHandle() }
                    MenuNavigationView("Tela MenuNavigation", rememberNavController(), userViewModel, agendaViewModel, empresaViewModel, servicoViewModel)
                }
            }
        }
    }
}


@Composable
fun MenuNavigationView(name: String, navController: NavHostController, userViewModel: UserViewModel, agendaViewModel: AgendaViewModel, empresaViewModel: EmpresaViewModel, servicoViewModel: ServicoViewModel,
                        modifier: Modifier = Modifier) {
    val navControllers = remember {
        NavMenuEnum.values().associateWith {
            mutableStateOf(false)
        }
    }
//    val tokenFlow = remember { SessionManager.getTokenFlow() }
//    val tokenState = remember { mutableStateOf<String?>(null) }
//    LaunchedEffect(tokenFlow) {
//        tokenFlow.collect { token ->
//            tokenState.value = token
//        }
//    }
//    Text(
//        text = tokenState.value ?: "Token não disponível"
//    )

    HideSystemBars()

    val isEmpresaActive = remember {
        navControllers[NavMenuEnum.EMPRESA]?.value ?: false
    }

    Column(modifier = modifier.fillMaxSize()) {

        Box(modifier = Modifier.weight(1f).fillMaxWidth().background(color = Color.White)) {
                NavHostWithScreens(navController = navController, userViewModel = userViewModel, agendaViewModel = agendaViewModel, empresaViewModel = empresaViewModel, servicoViewModel = servicoViewModel,
                    )
        }

        Row(
            modifier = Modifier
                .background(color = Color(0xFF041720))
        ) {

            NavMenuEnum.values().filter { tela ->
            tela != NavMenuEnum.EMPRESA
        }.forEach { tela ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .height(90.dp)
                        .background(color = Color.Transparent)
                        .clickable {
                            navControllers.forEach { it.value.value = false };
                            navControllers[tela]?.value = true;
                            navController.navigate(tela.name)
                        }
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                color = if (navControllers[tela]?.value == true) Color(0xFF5F8D4E) else Color(
                                    0xFF05131C
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = tela.imagem),
                            contentDescription = tela.descricao,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(text = tela.nom_rota,
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavHostWithScreens(navController: NavHostController, userViewModel: UserViewModel, agendaViewModel: AgendaViewModel, empresaViewModel: EmpresaViewModel, servicoViewModel: ServicoViewModel,) {
    NavHost(
        navController = navController,
        startDestination = NavMenuEnum.HOME.name
    ) {
        composable(NavMenuEnum.HOME.name) {
            HomeComponent(empresaViewModel, navController)
        }
        composable(NavMenuEnum.SEARCH.name) {
            SearchComponent(empresaViewModel, navController)
        }
//        composable(NavMenuEnum.SEARCH.name, arguments = listOf(navArgument("filtro") {
//            type = NavType.StringType
//            defaultValue = ""
//            nullable = true
//        })) { backStackEntry ->
//            SearchComponent(
//                empresaViewModel = empresaViewModel,
//                navController = navController,
//                savedStateHandle = savedStateHandle
//            )
//        }
//        composable(
//            route = "Search/{filtroHome}",
//            arguments = listOf(navArgument("filtroHome") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val filtroHome = backStackEntry.arguments?.getString("filtroHome")
//            if (filtroHome != null) {
//                SearchComponent(empresaViewModel = empresaViewModel, navController = navController, filtroHome = filtroHome)
//            } else {
//            }
//        }
        composable(NavMenuEnum.CALENDAR.name) {
            CalendarComponent(agendaViewModel, empresaViewModel)
        }
        composable(NavMenuEnum.USER.name) {
            UserComponent(userViewModel)
        }
        composable(
                route = "Empresa/{idEmpresa}",
                arguments = listOf(navArgument("idEmpresa") { type = NavType.IntType })
            ) { backStackEntry ->
            val idEmpresa = backStackEntry.arguments?.getInt("idEmpresa")
            if (idEmpresa != null) {
                EmpresaComponent(navController = navController, idEmpresa = idEmpresa, servicoViewModel = servicoViewModel, empresaViewModel = empresaViewModel)
            } else {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    val fakeUserViewModel = UserViewModel()
    val fakeAgendaViewModel = AgendaViewModel()
    val fakeEmpresaViewModel = EmpresaViewModel()
    val fakeServicoViewModel = ServicoViewModel()

    TopHairTheme {
//        val navController = rememberNavController()
//        val savedStateHandle = remember { SavedStateHandle() }
        MenuNavigationView("Tela MenuNavigation",rememberNavController(), fakeUserViewModel, fakeAgendaViewModel, fakeEmpresaViewModel,fakeServicoViewModel)
    }
}