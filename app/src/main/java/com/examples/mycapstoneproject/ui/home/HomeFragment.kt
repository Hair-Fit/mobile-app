package com.examples.mycapstoneproject.ui.home

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.examples.mycapstoneproject.ui.camera.CameraActivity
import com.examples.mycapstoneproject.databinding.FragmentHomeBinding
import com.examples.mycapstoneproject.ui.rotateFile
import java.io.File


class HomeFragment : Fragment() {
    private lateinit var navController: NavController
    private var getFile: File? = null

    private var _binding: FragmentHomeBinding? = null
    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireActivity())
            .load("https://i.pinimg.com/736x/cf/e7/16/cfe716fa1969bb95e85174dc86e127d1--oval-face-shapes-oval-faces.jpg")
            .circleCrop()
            .into(binding.ivProfil)


        binding.btnScan.setOnClickListener {
            startCameraX()
        }
        binding.btnCatalog.setOnClickListener {view->


            val toCatalog = HomeFragmentDirections.actionNavigationHomeToNavigationCatalog()

            view.findNavController().navigate(toCatalog)
        }
        binding.btnProfile.setOnClickListener {
            val toProfile = HomeFragmentDirections.actionNavigationHomeToNavigationProfile()
            it.findNavController().navigate(toProfile)
        }
        binding.btnHistory.setOnClickListener {
            val toHistory = HomeFragmentDirections.actionNavigationHomeToNavigationHistory()
            it.findNavController().navigate(toHistory)
        }
//        (
//           val toCatalog = Cay
//            view.findNavController().navigate(toDetailCategoryFragment)
//            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_navigation_catalog)
//            )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun startCameraX() {
        val intent = Intent(getActivity(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }
    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            myFile?.let { file ->
                rotateFile(file, isBackCamera)
                getFile = file
//                binding.previewImageView.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }
}