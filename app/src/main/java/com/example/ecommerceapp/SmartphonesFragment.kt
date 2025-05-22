import android.os.Bundle
import retrofit2.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.ProductAdapter
import com.example.ecommerceapp.SimpleTextAdapter
import com.example.ecommerceapp.databinding.FragmentSmartphonesBinding
import com.example.ecommerceapp.models.ProductResponse
import com.example.ecommerceapp.remote.ApiClient
import retrofit2.Response
import retrofit2.Callback

class SmartphonesFragment : Fragment() {

    private lateinit var binding: FragmentSmartphonesBinding

    private val phoneList = listOf(
        "Samsung Galaxy S24",
        "iPhone 15 Pro",
        "OnePlus 12",
        "Pixel 8 Pro",
        "Realme GT Neo",
        "Nothing Phone 2"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSmartphonesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvPhones.layoutManager = LinearLayoutManager(requireContext())

        ApiClient.instance.getProductsBySubCategory(1).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == 0) {
                    val products = response.body()?.products ?: emptyList()
                    binding.rvPhones.adapter = ProductAdapter(products)
                } else {
                    Toast.makeText(requireContext(), "Failed to load products", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

