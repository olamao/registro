package com.example.work.data.despachos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "despachos")
data class Despacho(
    @PrimaryKey(autoGenerate = false)
    val indexMunPro: Int,

    @ColumnInfo(name = "municipio")
    val municipio: Int,

    @ColumnInfo(name = "producto")
    val producto: Int,

    @ColumnInfo(name = "m202101")
    val m202101: Int,

    @ColumnInfo(name = "m202102")
    val m202102: Int,

    @ColumnInfo(name = "m202103")
    val m202103: Int,

    @ColumnInfo(name = "m202104")
    val m202104: Int,

    @ColumnInfo(name = "m202105")
    val m202105: Int,

    @ColumnInfo(name = "m202106")
    val m202106: Int,

    @ColumnInfo(name = "m202107")
    val m202107: Int,

    @ColumnInfo(name = "m202108")
    val m202108: Int,

    @ColumnInfo(name = "m202109")
    val m202109: Int,

    @ColumnInfo(name = "m202110")
    val m202110: Int,

    @ColumnInfo(name = "m202111")
    val m202111: Int,

    @ColumnInfo(name = "m202112")
    val m202112: Int,



    @ColumnInfo(name = "m202201")
    val m202201: Int,

    @ColumnInfo(name = "m202202")
    val m202202: Int,

    @ColumnInfo(name = "m202203")
    val m202203: Int,

    @ColumnInfo(name = "m202204")
    val m202204: Int,

    @ColumnInfo(name = "m202205")
    val m202205: Int,

    @ColumnInfo(name = "m202206")
    val m202206: Int,

    @ColumnInfo(name = "m202207")
    val m202207: Int,

    @ColumnInfo(name = "m202208")
    val m202208: Int,

    @ColumnInfo(name = "m202209")
    val m202209: Int,

    @ColumnInfo(name = "m202210")
    val m202210: Int,

    @ColumnInfo(name = "m202211")
    val m202211: Int,

    @ColumnInfo(name = "m202212")
    val m202212: Int,



    @ColumnInfo(name = "m202301")
    val m202301: Int,

    @ColumnInfo(name = "m202302")
    val m202302: Int,

    @ColumnInfo(name = "m202303")
    val m202303: Int,

    @ColumnInfo(name = "m202304")
    val m202304: Int,

    @ColumnInfo(name = "m202305")
    val m202305: Int,

    @ColumnInfo(name = "m202306")
    val m202306: Int,

    @ColumnInfo(name = "m202307")
    val m202307: Int,

    @ColumnInfo(name = "m202308")
    val m202308: Int,

    @ColumnInfo(name = "m202309")
    val m202309: Int,

    @ColumnInfo(name = "m202310")
    val m202310: Int,

    @ColumnInfo(name = "m202311")
    val m202311: Int,

    @ColumnInfo(name = "m202312")
    val m202312: Int,



    @ColumnInfo(name = "m202401")
    val m202401: Int,

    @ColumnInfo(name = "m202402")
    val m202402: Int,

    @ColumnInfo(name = "m202403")
    val m202403: Int,

    @ColumnInfo(name = "m202404")
    val m202404: Int,

    @ColumnInfo(name = "m202405")
    val m202405: Int,

    @ColumnInfo(name = "m202406")
    val m202406: Int,

    @ColumnInfo(name = "m202407")
    val m202407: Int,

    @ColumnInfo(name = "m202408")
    val m202408: Int,

    @ColumnInfo(name = "m202409")
    val m202409: Int,

    @ColumnInfo(name = "m202410")
    val m202410: Int,

    @ColumnInfo(name = "m202411")
    val m202411: Int,

    @ColumnInfo(name = "m202412")
    val m202412: Int,



    @ColumnInfo(name = "m202501")
    val m202501: Int,

    @ColumnInfo(name = "m202502")
    val m202502: Int,

    @ColumnInfo(name = "m202503")
    val m202503: Int,

    @ColumnInfo(name = "m202504")
    val m202504: Int,





    )