package com.unam.appredsocialig

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.unam.appredsocialig.ui.ListViewBase
import com.unam.appredsocialig.ui.NavigationActivity

abstract class BaseFragment <VB : ViewBinding>(
    layout : Int,
    private val setupViewBinding : (View) -> VB,
): Fragment(layout), ListViewBase {

    protected var progressDialog : Dialog? = null
    protected var errorDialog : MaterialAlertDialogBuilder? = null

    val activity: AppCompatActivity by lazy {
        requireActivity() as NavigationActivity
    }

    private var _mContext: Context? = null
    val mContext: Context get() = _mContext ?: throw IllegalStateException("Context no disponible")

    private var _viewBinding: VB? = null
    val binding: VB get() = _viewBinding ?: throw IllegalStateException("View Binding no disponible")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = setupViewBinding(view)
        _mContext = binding.root.context
        initElements()
    }

    override fun onDestroyView() {
        _viewBinding = null
        _mContext = null
        super.onDestroyView()
    }

    override fun showAlert() {
        try {
            progressDialog = loadingDialog(requireContext())
        }catch (e : java.lang.Exception){
            e.printStackTrace()
        }
    }

    override fun hideAlert() {
        try{
            progressDialog?.let {
                if(it.isShowing)
                    it.cancel()
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    protected abstract fun initElements()

    fun showCollapsingToolBar(show: Boolean = false) {
        val activity = (activity as NavigationActivity)
        if (show) {
            activity.binding.collapsingToolbar.visibility = View.VISIBLE
            activity.binding.toolbar.visibility = View.VISIBLE
        } else {
            activity.binding.collapsingToolbar.visibility = View.GONE
            setRelativePanelDimen()
        }
    }

    private fun setRelativePanelDimen(option: Int = 1) {
        when (option) {
            1 -> (activity as NavigationActivity).binding.relativePanel.layoutParams.height = pixels20
            2 -> (activity as NavigationActivity).binding.relativePanel.layoutParams.height = pixels200
            3 -> (activity as NavigationActivity).binding.relativePanel.layoutParams.height = pixels150
        }
    }

    private fun loadingDialog(context: Context): Dialog {
        val progressDialog = Dialog(context,R.style.FullScreenDialog)
        progressDialog.let {
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setContentView(R.layout.progress_dialog)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.show()
            return it
        }
    }

    private val scale by lazy {
        this.resources.displayMetrics.density // Escala de la pantalla
    }

    private val pixels20 by lazy {
        (20 * scale + 0.5f).toInt()
    }

    private val pixels150 by lazy {
        (150 * scale + 0.5f).toInt()
    }

    private val pixels200 by lazy {
        (200 * scale + 0.5f).toInt()
    }
}