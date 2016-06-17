package com.ap.jesus.migsv2;

import java.util.ArrayList;
import java.util.List;
import graph.Label;
import graph.Pair;

/**
 * Datos de prueba para las pestañas
 */
public class POIs {

    private static POI[] POIs;

    public static void setPOIs(POI[] newPOIs){
        POIs = newPOIs;
    }

    public static void setPOIs(){
        POIs = new POI[52];

        List<Pair<Label, Double>> list = new ArrayList<>();
        list.add(new Pair<>(Label.historia, 0.95));
        list.add(new Pair<>(Label.espana, 0.9));
        list.add(new Pair<>(Label.fdi, 0.95));
        POIs[0] = (new POI("Cartel de García Santesmases", R.drawable.a1garciasantesmases, "",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.historia, 0.8));
        list.add(new Pair<>(Label.espana, 0.85));
        list.add(new Pair<>(Label.hardware, 0.85));
        list.add(new Pair<>(Label.fdi, 0.85));
        list.add(new Pair<>(Label.pcs, 0.7));
        POIs[1] = (new POI("Supercomputador Cerebro", R.drawable.a2analizadordiferencial, "",
                list, 3, "https://www.youtube.com/watch?v=lHG8weZIh0M"));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.historia, 0.3));
        list.add(new Pair<>(Label.fdi, 0.7));
        list.add(new Pair<>(Label.pcs, 0.7));
        POIs[2] = (new POI("IBM Versión comercial", R.drawable.a3ibmcomercial, "",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.historia, 0.7));
        list.add(new Pair<>(Label.espana, 0.8));
        list.add(new Pair<>(Label.pcs, 0.5));
        POIs[3] = (new POI("IEA-FI(1973)", R.drawable.a4ieafi, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.fdi, 0.3));
        list.add(new Pair<>(Label.docencia, 0.2));
        POIs[4] = (new POI("Motorola Exor-ciser", R.drawable.a5exorciser, "La primera calculadora electrónica de sobremesa data de 1961 y realizaba únicamente sumas, restas, multiplicaciones y divisiones. No es hasta 1965 cuando se lanza esta calculadora que fue la primera capaz de realizar además raíces cuadradas. Utiliza notación polaca inversa, razón por la cual no dispone de la tecla “=”. Los operandos y resultados se almacenan en una memoria de tipo línea de retardo acústica. Para calcular la raíz cuadrada usa un algoritmo iterativo, por lo que el tiempo de cálculo de esta operación es variable y tarda desde pocos milisegundos hasta 2,5 segundos.\n",
                list, 3, "http://www.hpmuseum.org/ec132.htm"));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.fdi, 0.3));
        list.add(new Pair<>(Label.videojuegos, 0.2));
        list.add(new Pair<>(Label.cine, 0.3));
        POIs[5] = (new POI("IBM 7090 ", R.drawable.a6ibm7090, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.docencia, 0.6));
        list.add(new Pair<>(Label.videojuegos, 0.4));
        list.add(new Pair<>(Label.cine, 0.3));
        POIs[6] = (new POI("Cursos de automática de García Santesmases ", R.drawable.a1garciasantesmases, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.docencia, 0.8));
        list.add(new Pair<>(Label.fdi, 0.7));
        list.add(new Pair<>(Label.espana, 0.3));
        list.add(new Pair<>(Label.historia, 0.3));
        POIs[7] = (new POI("Enseñanza asistida por computador ", R.drawable.a8ensenanzaordenador, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.cine, 0.2));
        list.add(new Pair<>(Label.ciencias, 0.5));
        POIs[8] = (new POI("Calculadoras ", R.drawable.a9calculators, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.hardware, 0.7));
        list.add(new Pair<>(Label.pcs, 0.3));
        list.add(new Pair<>(Label.ciencias, 0.2));
        POIs[9] = (new POI("Friden EC-132  ", R.drawable.a10friden, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.curiosidad, 0.5));
        list.add(new Pair<>(Label.ciencias, 0.3));
        list.add(new Pair<>(Label.hardware, 0.2));
        POIs[10] = (new POI("Calculadora de raíces cuadradas ", R.drawable.a11calculator, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.ciencias, 0.5));
        POIs[11] = (new POI("PDA's y tablets", R.drawable.a12pdas, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.perifericos, 0.7));
        POIs[12] = (new POI("Periféricos de IBM", R.drawable.a13ibmperif, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.fdi, 0.8));
        list.add(new Pair<>(Label.arte, 0.9));
        list.add(new Pair<>(Label.ciencias, 0.9));
        POIs[13] = (new POI("Arte por computadora", R.drawable.a14computerart, " ",
                list, 3,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.cine, 0.7));
        list.add(new Pair<>(Label.curiosidad, 0.6));
        POIs[14] = (new POI("Unidades de cinta y discos físicos ", R.drawable.a15unidadcinta, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.curiosidad, 0.5));
        list.add(new Pair<>(Label.fdi, 0.3));
        POIs[15] = (new POI("Teléfonos", R.drawable.a16telefnos, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.videojuegos, 0.3));
        list.add(new Pair<>(Label.curiosidad, 0.3));
        POIs[16] = (new POI("Sony AIBO ERS-7", R.drawable.a17sonyaibo, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.fdi, 0.3));
        list.add(new Pair<>(Label.curiosidad, 0.2));
        list.add(new Pair<>(Label.redes, 0.8));
        POIs[17] = (new POI("PDP 11", R.drawable.a18pdp11, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.7));
        list.add(new Pair<>(Label.curiosidad, 0.3));
        POIs[18] = (new POI("Ordenadores circa 1990", R.drawable.a19amstrad, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.8));
        list.add(new Pair<>(Label.historia, 0.3));
        list.add(new Pair<>(Label.curiosidad, 0.4));
        list.add(new Pair<>(Label.cine, 0.2));
        POIs[19] = (new POI("Memorex 1337 + Fichas perforadas", R.drawable.a20fichasperforadas, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.docencia, 0.4));
        POIs[20] = (new POI("Carteles", R.drawable.a21carteles, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.7));
        list.add(new Pair<>(Label.redes, 0.4));
        list.add(new Pair<>(Label.cine, 0.35));
        POIs[21] = (new POI("HP 21MX", R.drawable.a22hp21mx, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.curiosidad, 0.7));
        list.add(new Pair<>(Label.cine, 0.4));
        list.add(new Pair<>(Label.docencia, 0.8));
        list.add(new Pair<>(Label.perifericos, 0.2));
        POIs[22] = (new POI("Simulador de Centrales Térmicas", R.drawable.a23centraltermica, " ",
                list, 3,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.8));
        POIs[23] = (new POI("IBM-PC XT 1983", R.drawable.a24ibmxt, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.5));
        POIs[24] = (new POI("Plotter HP9872C", R.drawable.a25plotterhp, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.65));
        POIs[25] = (new POI("HP 9000/319", R.drawable.a26hp9000, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.redes, 0.7));
        POIs[26] = (new POI("HP 1000", R.drawable.a27hp1000, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.videojuegos, 0.95));
        list.add(new Pair<>(Label.curiosidad, 0.7));
        list.add(new Pair<>(Label.arte, 0.85));
        list.add(new Pair<>(Label.espana, 0.85));
        list.add(new Pair<>(Label.pcs, 0.8));
        POIs[27] = (new POI("Ordenadores Amstrad", R.drawable.a19amstrad, " ",
                list, 4,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.videojuegos, 0.95));
        list.add(new Pair<>(Label.curiosidad, 0.3));
        list.add(new Pair<>(Label.fdi, 0.6));
        list.add(new Pair<>(Label.cine, 0.3));
        list.add(new Pair<>(Label.historia, 0.3));
        POIs[28] = (new POI("Consolas Atari y Nintendo", R.drawable.a29atari, " ",
                list, 3,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.historia, 0.3));
        list.add(new Pair<>(Label.videojuegos, 0.85));
        list.add(new Pair<>(Label.espana, 0.4));
        list.add(new Pair<>(Label.pcs, 0.8));
        POIs[29] = (new POI("Ordenadores Sony, Amstrad...", R.drawable.a19amstrad, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.curiosidad, 0.5));
        list.add(new Pair<>(Label.cine, 0.7));
        list.add(new Pair<>(Label.videojuegos, 0.95));
        POIs[30] = (new POI("Máquina recreativa", R.drawable.a31maquinarecreativa, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.fdi, 0.4));
        list.add(new Pair<>(Label.hardware, 0.6));
        POIs[31] = (new POI("Cables", R.drawable.a32cables, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.fdi, 0.4));
        list.add(new Pair<>(Label.hardware, 0.95));
        POIs[32] = (new POI("Procesadores de Intel", R.drawable.a33intel, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.ciencias, 0.8));
        list.add(new Pair<>(Label.hardware, 0.7));
        POIs[33] = (new POI("IBM 3097: Refrigeración líquida", R.drawable.a34refrigeracion, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.almacenamiento, 0.85));
        POIs[34] = (new POI("Disco Duro 8 GB", R.drawable.a35discoduro, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.cine, 0.5));
        list.add(new Pair<>(Label.almacenamiento, 0.95));
        list.add(new Pair<>(Label.historia, 0.2));
        POIs[35] = (new POI("Plato 6 MB", R.drawable.a36discoviejo, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.almacenamiento, 0.8));
        list.add(new Pair<>(Label.historia, 0.3));
        POIs[36] = (new POI("Memorias de ferrita y otros dispositivos de almacenamiento", R.drawable.a37ferrita, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.hardware, 0.95));
        list.add(new Pair<>(Label.curiosidad, 0.3));
        list.add(new Pair<>(Label.almacenamiento, 0.3));
        POIs[37] = (new POI("Transistores, ROM, Circuitos integrados, Obleas", R.drawable.a38oblea, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.redes, 0.3));
        list.add(new Pair<>(Label.cine, 0.3));
        list.add(new Pair<>(Label.perifericos, 0.5));
        POIs[38] = (new POI("Periféricos y conectividad", R.drawable.a39periferico, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.perifericos, 0.9));
        list.add(new Pair<>(Label.cine, 0.2));
        POIs[39] = (new POI("Disquetes 3½, 5¼ ", R.drawable.a40disquetes, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.perifericos, 0.7));
        POIs[40] = (new POI("Impresoras", R.drawable.a41impresorasviejas, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.perifericos, 0.8));
        POIs[41] = (new POI("Periféricos: ratones, teclados, lapiz", R.drawable.a39periferico, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.almacenamiento, 0.85));
        POIs[42] = (new POI("Discos duros", R.drawable.a43discosduros, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.almacenamiento, 0.9));
        POIs[43] = (new POI("Discos duros antiguos", R.drawable.a43discosduros, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.95));
        list.add(new Pair<>(Label.historia, 0.3));
        list.add(new Pair<>(Label.videojuegos, 0.35));
        POIs[44] = (new POI("PC's", R.drawable.a19amstrad, " ",
                list, 3,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.95));
        list.add(new Pair<>(Label.perifericos, 0.3));
        POIs[45] = (new POI("IBM 5110 Computing System", R.drawable.a46computing, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.servidores, 0.85));
        list.add(new Pair<>(Label.redes, 0.8));
        POIs[46] = (new POI("DEC AlphaServer 2100", R.drawable.a47alphaserver, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.pcs, 0.95));
        list.add(new Pair<>(Label.cine, 0.5));
        list.add(new Pair<>(Label.curiosidad, 0.5));
        POIs[47] = (new POI("Ordenadores de Apple", R.drawable.a48apple, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.servidores, 0.9));
        list.add(new Pair<>(Label.redes, 0.8));
        POIs[48] = (new POI("Servidores Unix", R.drawable.a49unix, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.servidores, 0.9));
        list.add(new Pair<>(Label.hardware, 0.6));
        POIs[49] = (new POI("Cray Y-MP EL", R.drawable.a50cray, " ",
                list, 2,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.fdi, 0.7));
        list.add(new Pair<>(Label.servidores, 0.8));
        POIs[50] = (new POI("Origin 2000", R.drawable.a51origin, " ",
                list, 1,""));

        list = new ArrayList<>();
        list.add(new Pair<>(Label.servidores, 0.9));
        POIs[51] = (new POI("Servidores actuales", R.drawable.a52servidores, " ",
                list, 1,""));

    }


    public static List<POI> getSamplePOIs(){
        List<POI> items = new ArrayList<>();
        for (int i = 0; i < POIs.length; i++){
            items.add(POIs[i]);
        }
        return items;
    }
    public static POI[] getPOIs() {
        return POIs;
    }
    public static List<POI> getPOIsArray() {
        ArrayList<POI> al = new ArrayList<>();
        for (int i = 0; i<POIs.length; i++){
            al.add(POIs[i]);
        }
        return al;
    }

    public static POI getPOI(int i){
        return POIs[i-1];
    }
    public static POI getPOI(String teamName){
        for (POI t: POIs){
            if (t.getName().equalsIgnoreCase(teamName)){
                return t;
            }
        }
        return null;
    }


}
