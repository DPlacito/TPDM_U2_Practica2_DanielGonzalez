package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.compra

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_actualizar_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Actualizar_compra : AppCompatActivity() {
    private var edit_buscar_actualizar: EditText? = null
    private var edit_actualiza_fecha: EditText? = null
    private var edit_actualizar_total: EditText? = null
    private var boton_actualizar: Button? = null
    private var boton_buscar : Button?=null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_compra)
        setSupportActionBar(cabezera_Actualizar_Compra)
        title = "Actualizar Compra"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_buscar_actualizar = findViewById(R.id.edit_Buscar_Actualizar_Compra)
        edit_actualiza_fecha = findViewById(R.id.edit_Fecha_Actualizar_Compra)
        edit_actualizar_total = findViewById(R.id.edit_Total_Actualizar_Compra)
        boton_actualizar = findViewById(R.id.boton_Actualizar_Compra)
        boton_buscar= findViewById(R.id.boton_Buscar_Actualizar_Compra)

        boton_buscar?.setOnClickListener {
            buscar(edit_buscar_actualizar?.text.toString())
        }

        boton_actualizar?.setOnClickListener {
            aplicarActualizar()
        }
    }

    private fun buscar(id: String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "SELECT * FROM COMPRA WHERE ID_COMPRA= "+id

            var respuesta = transaccion.rawQuery(SQL, null)
            if (respuesta.moveToFirst() == true) {
                var Traefecha = respuesta.getString(1)
                var Traetotal = respuesta.getString(2)

                edit_actualiza_fecha?.setText(Traefecha)
                edit_actualizar_total?.setText(Traetotal)
                transaccion.close()
            }
        } catch (err: SQLiteException) {
            mensaje("ERROR", "AL PARECER NO EXISTE LA DESCRIPCION")
        }
    }

    private fun aplicarActualizar() {
        try {
            var transaccion = basedatos.writableDatabase
            var SQL =
                "UPDATE COMPRA SET  FECHA ='CAMPOFECHA', TOTAL ='CAMPOTOTAL ' WHERE ID_COMPRA=ID_COMPRA "

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")
                return
            }
            SQL = SQL.replace("CAMPOFECHA", edit_actualiza_fecha?.text.toString())
            SQL = SQL.replace("CAMPOTOTAL", edit_actualizar_total?.text.toString())

            transaccion.execSQL(SQL)
            transaccion.close()//CIERRA LA TRANSACCION
            mensaje("ACTUALIZADO", "SE ACTUALIZO CORRECTAMENTE")
            limpiar()
        } catch (err: SQLiteException) {
            mensaje("Error", "NO SE PUDO INSERTAR, TAL VEZ ID YA EXISTE")
        }
    }

    fun validarCampos(): Boolean {
        if (edit_actualiza_fecha?.text.toString().isEmpty() || edit_actualizar_total?.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("Ok") { dialog, whitch -> }
            .show()
    }

    fun limpiar() {
        edit_buscar_actualizar?.setText("")
        edit_actualiza_fecha?.setText("")
        edit_actualizar_total?.setText("")
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

