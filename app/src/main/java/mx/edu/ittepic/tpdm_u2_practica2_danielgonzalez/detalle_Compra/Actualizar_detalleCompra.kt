package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.detalle_Compra

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_actualizar_detalle_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Actualizar_detalleCompra : AppCompatActivity() {

    private var edit_buscar_actualizar: EditText? = null
    private var edit_actualiza_cantidad: EditText? = null
    private var edit_actualizar_precio: EditText? = null
    private var boton_actualizar: Button? = null
    private var boton_buscar: Button? = null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_detalle_compra)
        setSupportActionBar(cabezera_Actualizar_DC)
        title = "Actualizar_Almacen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_buscar_actualizar = findViewById(R.id.edit_Buscar_Actualizar_DC)
        edit_actualiza_cantidad = findViewById(R.id.edit_Cantidad_Actualizar_DC)
        edit_actualizar_precio = findViewById(R.id.edit_Precio_Actualizar_DC)
        boton_actualizar = findViewById(R.id.boton_Actualizar_DC)
        boton_buscar = findViewById(R.id.boton_Buscar_Actualizar_DC)

        boton_buscar?.setOnClickListener {
            buscar(edit_buscar_actualizar?.text.toString())
        }

        boton_actualizar?.setOnClickListener {
            aplicarActualizar()
        }
    }

    private fun buscar(ID: String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "SELECT * FROM DETALLECOMPRA WHERE ID_DETALLE= "+ID

            var respuesta = transaccion.rawQuery(SQL, null)
            if (respuesta.moveToFirst() == true) {
                var Traecantidad = respuesta.getString(1)
                var Traeprecio = respuesta.getString(2)

                edit_actualiza_cantidad?.setText(Traecantidad)
                edit_actualizar_precio?.setText(Traeprecio)
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
                "UPDATE DETALLECOMPRA SET  FECHA ='CAMPOCANTIDAD', PRECIO ='CAMPOPRECIO' WHERE ID_DETALLE=ID_DETALLE "

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")
                return
            }
            SQL = SQL.replace("CAMPOCANTIDAD", edit_actualiza_cantidad?.text.toString())
            SQL = SQL.replace("CAMPOPRECIO", edit_actualizar_precio?.text.toString())

            transaccion.execSQL(SQL)
            transaccion.close()//CIERRA LA TRANSACCION
            mensaje("ACTUALIZADO", "SE ACTUALIZO CORRECTAMENTE")
            limpiar()
        } catch (err: SQLiteException) {
            mensaje("Error", "NO SE PUDO INSERTAR, TAL VEZ ID YA EXISTE")
        }
    }

    fun validarCampos(): Boolean {
        if (edit_actualiza_cantidad?.text.toString().isEmpty() || edit_actualizar_precio?.text.toString().isEmpty()) {
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
        edit_actualiza_cantidad?.setText("")
        edit_actualizar_precio?.setText("")
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

