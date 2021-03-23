package io.github.fuadreza.simplesingleactivityarchitecture.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import io.github.fuadreza.simplesingleactivityarchitecture.utils.data.autoCleared
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), IBaseView {
    protected var binding: T by autoCleared()
    private var toast: Toast? = null
    private var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    override fun onDestroyView() {
        snackbar?.dismiss()
        toast?.cancel()
        super.onDestroyView()
    }

    protected fun <T> observeFlow(
        flow: Flow<T>,
        observeDispatcher: CoroutineDispatcher? = null,
        observer: suspend (T) -> Unit
    ) {
        lifecycleScope.launch(observeDispatcher ?: EmptyCoroutineContext) {
            flow
                .onEach {
                    try {
                        observer.invoke(it)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        showSnackbar(text = "Observe Stream -> ${e.message ?: "Error"}")
                    }
                }
                .collect()
        }
    }

    protected fun showSnackbar(
        text: String, @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_LONG
        , actionText: String? = null, actionClick: View.OnClickListener? = null
    ) {
        snackbar?.dismiss()
        snackbar =
            Snackbar.make(requireActivity().findViewById(android.R.id.content), text, duration)
        if (actionText != null && actionClick != null) {
            snackbar!!.setAction(actionText, actionClick)
        }
        snackbar!!.show()
    }

    protected fun showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
        toast?.cancel()
        toast = Toast.makeText(requireContext(), text, duration)
        toast!!.show()
    }
}