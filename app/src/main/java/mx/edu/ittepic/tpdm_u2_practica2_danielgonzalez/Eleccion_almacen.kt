package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_eleccion_almacen.*

class Eleccion_almacen : AppCompatActivity() {

    private var insertar: Button? = null
    private var actualizar: Button? = null
    private var eliminar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion_almacen)
        setSupportActionBar(cabezera_ELeccion)
        title = "Almacen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        insertar = findViewById(R.id.insertar)
        actualizar = findViewById(R.id.actualizar)
        eliminar = findViewById(R.id.eliminar)

        insertar?.setOnClickListener {
            var otroActivity  = Intent(this, Insertar_almacen::class.java)
            startActivity(otroActivity)
        }

        actualizar?.setOnClickListener {
            var otroActivity  = Intent(this, Actualizar_almacen::class.java)
            startActivity(otroActivity)
        }

        eliminar?.setOnClickListener {
            var otroActivity  = Intent(this, Eliminar_almacen::class.java)
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
