package com.example.testsmart.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testsmart.R
import com.example.testsmart.data.local.UserDatabase
import com.example.testsmart.data.model.UserEntity
import com.example.testsmart.databinding.FragmentDetailBinding
import com.example.testsmart.databinding.FragmentRegisterBinding
import com.example.testsmart.presentation.UserViewModel
import com.example.testsmart.repository.UserRepository

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnSave.setOnClickListener {
           insertDataToDataBase()
        }

    }

    private fun insertDataToDataBase(){

        val name = binding.edittextName.text.toString()
        val firstName = binding.editFirstName.text.toString()
        val lastName = binding.editLastName.text.toString()
        val fechaDeNacimiento = binding.editFechaDeNacimiento.text.toString()
        val lugarDeNacimiento = binding.editLugarDeNacimiento.text.toString()

        if (inputCheck(name, firstName, lastName, fechaDeNacimiento, lugarDeNacimiento)){
            val user = UserEntity(0
                , name
                ,firstName
                ,lastName
                ,fechaDeNacimiento
                ,lugarDeNacimiento)

            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Usuario Guardado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_registerFragment_to_main_graph)
        }else{
            Toast.makeText(requireContext(), "Por favor llene todos los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String
                           , firstName: String
                           , lastName: String
                           , fechaDeNacimiento: String
                           , lugarDeNacimiento: String): Boolean{

        return  !(TextUtils.isEmpty(name)
                && TextUtils.isEmpty(firstName)
                && TextUtils.isEmpty(lastName)
                && TextUtils.isEmpty(fechaDeNacimiento)
                &&TextUtils.isEmpty(lugarDeNacimiento))
    }

}