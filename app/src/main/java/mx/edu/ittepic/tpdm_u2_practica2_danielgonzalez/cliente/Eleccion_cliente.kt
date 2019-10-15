package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.cliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_eleccion_cliente.*
import kotlinx.android.synthetic.main.activity_eleccion_detalle_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Eleccion_cliente : AppCompatActivity() {

    private var insertar: Button? = null
    private var actualizar: Button? = null
    private var eliminar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion_cliente)

        setSupportActionBar(cabezera_Eleccion_C)
        title = "Cliente"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        insertar = findViewById(R.id.insertar_C)
        actualizar = findViewById(R.id.actualizar_C)
        eliminar = findViewById(R.id.eliminar_C)

        insertar?.setOnClickListener {
            var otroActivity  = Intent(this, Insertar_cliente::class.java)
            startActivity(otroActivity)
        }

        actualizar?.setOnClickListener {
            var otroActivity  = Intent(this, Actualizar_cliente::class.java)
            startActivity(otroActivity)
        }

        eliminar?.setOnClickListener {
            var otroActivity  = Intent(this, Eliminar_cliente::class.java)
            startActivity(otroActivity)
        }

    }
    //Regresar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else
            super.onOptionsItemSelected(item)
    }
}
