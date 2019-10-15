package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.detalle_Compra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_eleccion_detalle_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Eleccion_detalle_compra : AppCompatActivity() {

    private var insertar: Button? = null
    private var actualizar: Button? = null
    private var eliminar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion_detalle_compra)

        setSupportActionBar(cabezera_Eleccion_DC)
        title = "Detalle Compra"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        insertar = findViewById(R.id.insertar)
        actualizar = findViewById(R.id.actualizar)
        eliminar = findViewById(R.id.eliminar)

        insertar?.setOnClickListener {
            var otroActivity  = Intent(this, Insertar_detalleCompra::class.java)
            startActivity(otroActivity)
        }

        actualizar?.setOnClickListener {
            var otroActivity  = Intent(this, Actualizar_detalleCompra::class.java)
            startActivity(otroActivity)
        }

        eliminar?.setOnClickListener {
            var otroActivity  = Intent(this, Eliminar_detalleCompra::class.java)
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
