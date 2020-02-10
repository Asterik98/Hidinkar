package com.hwp.lingkungansehat.sakit

data class Penyakit(var id: Int?,
                    var nama: String?,
                    var keterangan : String?,
                    var perawatan : String?,
                    var pencegahan : String?,
                    var gejala: String?,
                    var dokter: String?,
                    var source : String?,
                    // 1 = Hipertensi, 2 = Diabetes, 3 = Kanker
                    var jenis : Int?){
    companion object {
        const val TABLE_PENYAKIT = "table_penyakit"
        const val ID = "id"
        const val NAMA = "nama"
        const val KETERANGAN = "keterangan"
        const val PERAWATAN = "perawatan"
        const val PENCEGAHAN = "pencegahan"
        const val GEJALA = "gejala"
        const val DOKTER = "dokter"
        const val SOURCE = "source"
        const val JENIS = "jenis"

    }
}