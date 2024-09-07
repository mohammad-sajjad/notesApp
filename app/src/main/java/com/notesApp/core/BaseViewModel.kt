package com.coreStructure.core

import androidx.lifecycle.ViewModel
import com.notesApp.app.AppControllerContract


abstract class BaseViewModel(val appControllerContract: AppControllerContract = AppControllerContract.get()): ViewModel()