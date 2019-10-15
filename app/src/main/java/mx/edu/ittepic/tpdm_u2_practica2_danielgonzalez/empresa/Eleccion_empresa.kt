package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.empresa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_eleccion_empresa.*
import kotlinx.android.synthetic.main.activity_insertar_empresa.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Eleccion_empresa : AppCompatActivity() {

    private var insertar: Button? = null
    private var actualizar: Button? = null
    private var eliminar: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion_empresa)
        setSupportActionBar(cabezera_ELeccion_E)
        title = "EMPRESA"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        insertar = findViewById(R.id.insertar_e)
        actualizar = findViewById(R.id.actualizar_e)
        eliminar = findViewById(R.id.eliminar_e)

        insertar?.setOnClickListener {
            var otroActivity  = Intent(this, Insertar_empresa::class.java)
            startActivity(otroActivity)
        }

        actualizar?.setOnClickListener {
            var otroActivity  = Intent(this, Actualizar_empresa::class.java)
            startActivity(otroActivity)
        }

        eliminar?.setOnClickListener {
            var otroActivity  = Intent(this, Eliminar_empresa::class.java)
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
