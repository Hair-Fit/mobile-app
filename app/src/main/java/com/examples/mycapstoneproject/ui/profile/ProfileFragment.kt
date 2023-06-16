package com.examples.mycapstoneproject.ui.profile

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.databinding.FragmentNotificationsBinding
import com.examples.mycapstoneproject.databinding.FragmentProfileBinding
import com.examples.mycapstoneproject.ui.notifications.NotificationsViewModel


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name: String =""
        Glide.with(requireActivity())
            .load("https://i.pinimg.com/736x/cf/e7/16/cfe716fa1969bb95e85174dc86e127d1--oval-face-shapes-oval-faces.jpg")
            .circleCrop()
            .into(binding.ivProfil)
        binding.edtName.setText("budi")
        binding.edtEmail.setText("budi@hotmail.com")
        binding.edtFace.setText("heart")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}