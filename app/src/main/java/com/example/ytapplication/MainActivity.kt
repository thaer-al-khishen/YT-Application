package com.example.ytapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ytapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentAction {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun onFirstFragmentButtonClicked(firstFragmentText: String) {
//        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
//            fragmentTransaction.replace(binding.fragmentContainerView.id, SecondFragment().apply {
//                arguments = Bundle().apply {
//                    putString("key", "Value from first fragment")
//                }
//            })
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }
        if (navController.currentDestination?.id == R.id.firstFragment) {
            navController.navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                sfInput = SecondFragmentInput(secondFragmentText = firstFragmentText)
            ))
        }

    }

    override fun onSecondFragmentButtonClicked() {
        if (navController.currentDestination?.id == R.id.secondFragment) {
            navController.navigate(SecondFragmentDirections.actionSecondFragmentToThirdFragment())
        }
    }

}
