package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.cliente.Eleccion_cliente
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.compra.Eleccion_compra
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.detalle_Compra.Eleccion_detalle_compra
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.empresa.Eleccion_empresa

class MainActivity : AppCompatActivity() {

    var btn_almacen :ImageButton?=null
    var btn_cliente :ImageButton?=null
    var btn_empresa :ImageButton?=null
    var btn_detalle_compra :ImageButton?=null
    var btn_compra :ImageButton?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(cabezera_Principal)
        title = "Modelo De N Tablas Con Foreign Key"

        btn_almacen = findViewById(R.id.Almacen)
        btn_cliente = findViewById(R.id.cliente)
        btn_empresa = findViewById(R.id.empresa)
        btn_detalle_compra = findViewById(R.id.detalecompra)
        btn_compra = findViewById(R.id.compra)

        btn_almacen?.setOnClickListener {
            var otroActivity  = Intent(this, Eleccion_almacen::class.java)
            startActivity(otroActivity)
        }

        btn_cliente?.setOnClickListener {
           var otroActivity  = Intent(this, Eleccion_cliente::class.java)
            startActivity(otroActivity)
        }

        btn_empresa?.setOnClickListener {
          var otroActivity  = Intent(this, Eleccion_empresa::class.java)
            startActivity(otroActivity)
        }

        btn_detalle_compra?.setOnClickListener {
            var otroActivity  = Intent(this, Eleccion_detalle_compra::class.java)
            startActivity(otroActivity)
        }

        btn_compra?.setOnClickListener {
            var otroActivity  = Intent(this, Eleccion_compra::class.java)
            startActivity(otroActivity)
        }
    }
}
