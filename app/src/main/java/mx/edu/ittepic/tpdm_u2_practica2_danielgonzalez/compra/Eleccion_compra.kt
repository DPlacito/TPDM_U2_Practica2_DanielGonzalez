package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.compra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_eleccion_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Eleccion_compra : AppCompatActivity() {
    private var insertar: Button? = null
    private var actualizar: Button? = null
    private var eliminar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion_compra)
        setSupportActionBar(cabezera_Eleccion_Compra)
        title = "Compra"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        insertar = findViewById(R.id.insertar_Compra)
        actualizar = findViewById(R.id.actualizar_Compra)
        eliminar = findViewById(R.id.eliminar_Compra)

        insertar?.setOnClickListener {
            var otroActivity  = Intent(this, Insertar_compra::class.java)
            startActivity(otroActivity)
        }

        actualizar?.setOnClickListener {
            var otroActivity  = Intent(this, Actualizar_compra::class.java)
            startActivity(otroActivity)
        }

        eliminar?.setOnClickListener {
            var otroActivity  = Intent(this, Eliminar_compra::class.java)
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
