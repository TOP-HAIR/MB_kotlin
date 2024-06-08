package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tophair.app.data.api.EmpresaApi
import com.example.tophair.app.data.entities.Empresa
import com.example.tophair.app.data.entities.EmpresaPorId
import com.example.tophair.app.data.service.RetrofitService
import com.example.tophair.app.data.service.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmpresaViewModel : ViewModel() {
    val empresaTop5: MutableLiveData<List<Empresa>> = MutableLiveData()
    val empresaFiltro: MutableLiveData<List<Empresa>> = MutableLiveData()
    val empresaHome: MutableLiveData<List<Empresa>> = MutableLiveData()
    val empresaGetId: MutableLiveData<EmpresaPorId?> = MutableLiveData()

    val apiToken: EmpresaApi = RetrofitService.getApiServiceWithToken(EmpresaApi::class.java)
    val erroApi = MutableLiveData("")

    init {
        getTop5MelhoresEmpresas()
        getHomeEmpresas()
        getFiltroEmpresas()
    }

    fun getTop5MelhoresEmpresas() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userId = withContext(Dispatchers.Main) {
                    SessionManager.getUserIdFlow().firstOrNull()
                }
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.getTop5MelhoresEmpresas(userId.toInt())
                    if (response.isSuccessful) {
                        val empresaBody = response.body()
                        empresaBody?.let {
                            empresaTop5.postValue(it)
                        }

                    } else {
                        Log.e("EmpresaViewModel", "erro no getTop5MelhoresEmpresas ${response}")
                        erroApi.postValue(response.errorBody()!!.string())
                    }
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Error in getTop5MelhoresEmpresas! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun getEmpresaById(empresaId: Int?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiToken.getEmpresaPeloId(empresaId)
                if (response.isSuccessful) {
                    val empresa = response.body()
                    Log.d("adwa", "${empresa}")
                    empresaGetId.postValue(empresa)

                } else {
                    Log.e("EmpresaViewModel", "Erro no getEmpresaById ${response}")
                    erroApi.postValue(response.errorBody()!!.string())
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Erro no getEmpresaById! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun getHomeEmpresas() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userId = withContext(Dispatchers.Main) {
                    SessionManager.getUserIdFlow().firstOrNull()
                }
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.getTop5MelhoresEmpresas(userId.toInt())

                    if (response.isSuccessful) {
                        val empresaBody = response.body()

                        empresaBody?.let {
                            empresaHome.postValue(it)
                        }

                    } else {
                        Log.e("EmpresaViewModel", "erro no getTop5MelhoresEmpresas ${response}")
                        erroApi.postValue(response.errorBody()!!.string())
                    }
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Error in getTop5MelhoresEmpresas! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun getFiltroEmpresas(estado: String = "", servico: String = "", nomeEmpresa: String = "") {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userId = withContext(Dispatchers.Main) {
                    SessionManager.getUserIdFlow().firstOrNull()
                }
                if (!userId.isNullOrEmpty()) {
                    val response =
                        apiToken.getFiltroEmpresas(userId.toInt(), estado, servico, nomeEmpresa)

                    if (response.isSuccessful) {
                        val empresaBody = response.body()

                        empresaBody?.let {
                            empresaFiltro.postValue(it)
                        }

                    } else {
                        Log.e("EmpresaViewModel", "erro no getFiltroEmpresas ${response}")
                        erroApi.postValue(response.errorBody()!!.string())
                    }
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Error in getFiltroEmpresas! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }
}