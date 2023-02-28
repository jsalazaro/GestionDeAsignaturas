package com.masanz.gda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nombre Apellidos
 */
public class Gestor {

    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;

    public Gestor() {
        registro = new TreeMap<>();
    }

    //region get/setRegistro para los tests
    public void setRegistro(TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro){
        this.registro = registro;
    }
    public TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> getRegistro() {
        return registro;
    }
    //endregion

    //region operaciones grupo 11-14

    /**
     * Si el grupo existe en el registro.
     * @param grupo Instancia de Grupo, puede ser null.
     * @return Devuelve null si el grupo es nulo o true o false dependiendo si la clave grupo existe en el registro.
     */
    public boolean existeGrupo(Grupo grupo) {
        // TODO: existeGrupo (11)
        if (grupo == null) return false;

        return registro.containsKey(grupo);
    }

    /**
     * Añade una entrada con el grupo y un HashMap nuevo asociado como valor
     * reemplazando a una entrada con la misma clave si ya existiese.
     * @param grupo Instancia de un grupo
     */
    public void anadirGrupo(Grupo grupo) {
        // TODO: anadirGrupo (12)
        if (grupo == null) return;

        registro.put(grupo, new HashMap<>());
    }

    /**
     * Devuelve un conjunto ordenado no repetido de grupos formado con
     * el conjunto de grupos que contiene el registro como claves.
     * @return TreeSet de grupos del registro
     */
    public TreeSet<Grupo> getGrupos() {
        // TODO: getGrupos (13)
        TreeSet<Grupo> arbol = new TreeSet<>();
        for (Grupo grupo : registro.keySet()) {
            arbol.add(grupo);
        }

        return arbol;
    }

    /**
     * Elimina la entrada del grupo indicado del registro.
     * @param grupo Instancia de Grupo.
     */
    public void borrarGrupo(Grupo grupo) {
        // TODO: borrarGrupo (14)
        registro.remove(grupo);
    }

    //endregion

    //region operaciones asignatura 21-25

    /**
     * Existe la asignatura en el grupo.
     * @param asignatura
     * @param grupo
     * @return Si existe la asignatura asociada al grupo indicado en el registro.
     */
    public boolean existeAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO: existeAsignaturaGrupo (21)
        if (existeGrupo(grupo)) {
            return registro.get(grupo).containsKey(asignatura);
        }
        else return false;
    }

    /**
     * Si el grupo no existe en el registro se deberá añadir dentro de este método.
     * Incorpora una lista de estudiantes nueva asociada a una asignatura al grupo indicado.
     * @param asignatura
     * @param grupo
     */
    public void anadirAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO: anadirAsignaturaGrupo (22)
        if (registro.get(grupo) == null) {
            anadirGrupo(grupo);
        }
        registro.get(grupo).put(asignatura,new ArrayList<>());
    }

    /**
     * Devuelve el conjunto de asignaturas asociadas al grupo indicado.
     * @param grupo
     * @return
     */
    public HashSet<Asignatura> getAsignaturas(Grupo grupo) {
        // TODO: getAsignaturas grupo (23)
        HashSet<Asignatura> asignaturaHashMap = new HashSet<>();
        if (existeGrupo(grupo)) {
           asignaturaHashMap.addAll(registro.get(grupo).keySet());
        }
        return asignaturaHashMap;
    }

    /**
     * Devuelve un conjunto de asignaturas no repetidas de todos los grupos.
     * @return
     */
    public HashSet<Asignatura> getAsignaturas() {
        // TODO: getAsignaturas todas (24)
        HashSet<Asignatura> asignaturaHashMap = new HashSet<>();
        for (Grupo grupo : registro.keySet()) {
            asignaturaHashMap.addAll(registro.get(grupo).keySet());
        }
        return asignaturaHashMap;
    }

    /**
     * Si existe la asignatura asociada al grupo elimina la asignatura y la lista de estudiantes asociada.
     * @param asignatura
     * @param grupo
     */
    public void borrarAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO: borrarAsignaturaGrupo (25)
        if (existeAsignaturaGrupo(asignatura,grupo)){
            registro.get(grupo).remove(asignatura);
            registro.get(grupo).remove(registro.get(grupo).values());
        }
    }

    //endregion

    //region operaciones estudiante 31-35

    /**
     * Devuelve la lista de estudiantes de la asignatura de un grupo si existe, sino devuelve null.
     * @param asignatura
     * @param grupo
     * @return
     */
    public ArrayList<Estudiante> getListaEstudiantesAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO: getListaEstudiantesAsignaturaGrupo (31)
        ArrayList<Estudiante> estudianteArrayList = new ArrayList<>();
        if (existeAsignaturaGrupo(asignatura,grupo)){
            estudianteArrayList.addAll(registro.get(grupo).get(asignatura));
            return estudianteArrayList;
        }
        return null;
    }

    /**
     * Si existe un/a estudiante en la asignatura de un grupo.
     * @param estudiante
     * @param asignatura
     * @param grupo
     * @return
     */
    public boolean existeEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO: existeEstudianteAsignaturaGrupo (32)
        if (existeAsignaturaGrupo(asignatura,grupo)) {
            return registro.get(grupo).get(asignatura).contains(estudiante);
        }
        return false;
    }

    /**
     * Devuelve la instancia del/a estudiante asociado/a a la asignatura indicada de un grupo
     * con la nota que tiene o null si no existe.
     * @param estudiante
     * @param asignatura
     * @param grupo
     * @return
     */
    public Estudiante getEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO: getEstudianteAsignaturaGrupo (33)
        ArrayList<Estudiante> estudianteArrayList = getListaEstudiantesAsignaturaGrupo(asignatura,grupo);
        if (estudiante == null) return null;
        if (estudianteArrayList == null) return null;
        if (!estudianteArrayList.contains(estudiante)) return null;
        int pos = estudianteArrayList.indexOf(estudiante);
        return estudianteArrayList.get(pos);
    }


    /**
     * Si el grupo no existiese se debería crear en este método.
     * Análogamente, si no existe la asignatura asociada al grupo.
     * Al añadir la/el estudiante a la lista de estudiantes asociado a la asignatura del grupo,
     * si la/el estudiante ya existía se debe actualizar su nota con la que hay en el parámetro del método y
     * sino agregar una referencia a esta instancia.
     * @param estudiante
     * @param asignatura
     * @param grupo
     */
    public void anadirEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO: anadirEstudianteAsignaturaGrupo (34)
        if (!existeGrupo(grupo)){
            anadirGrupo(grupo);
        }
        if (!existeAsignaturaGrupo(asignatura,grupo)){
            anadirAsignaturaGrupo(asignatura,grupo);
        }
        if (!existeEstudianteAsignaturaGrupo(estudiante,asignatura,grupo)){
            registro.get(grupo).get(asignatura).add(estudiante);
        } else getEstudianteAsignaturaGrupo(estudiante,asignatura,grupo).setNota(estudiante.getNota());
    }

    /**
     * Si existe la o el estudiante asociada/o a la asignatura asociada al grupo elimina a esta/este de la lista de estudiantes.
     * @param estudiante
     * @param asignatura
     * @param grupo
     */
    public void borrarEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO: borrarEstudianteAsignaturaGrupo (35)
        if(existeEstudianteAsignaturaGrupo(estudiante,asignatura,grupo)){
            registro.get(grupo).get(asignatura).remove(estudiante);
        }
    }

    //endregion

    //region listados estudiantes 41-43

    /**
     * Devuelve una lista de estudiantes no repetidos de un grupo.
     * No importa la nota del/a estudiante de qué asignatura sea (no se mostrará).
     * @param grupo
     * @return
     */
    public ArrayList<Estudiante> getEstudiantes(Grupo grupo) {
        // TODO: getEstudiantes grupo (41)
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        HashSet<Estudiante> estudianteHashSet = new HashSet<>();
        for (Map.Entry<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> entry : registro.entrySet()) {
            Grupo g = entry.getKey();
            HashSet<Asignatura> asignaturas = getAsignaturas(grupo);
            for (Asignatura asignatura : asignaturas) {
                estudiantes.addAll(registro.get(grupo).get(asignatura));
            }
        }
        ArrayList<Estudiante> estudiantes2 = estudiantes.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        if (estudiantes2.isEmpty()) return null;
        return estudiantes2;
    }

    /**
     * Devuelve una lista con todas/os las/los estudiantes que cursan una asignatura independientemente
     * del grupo al que pertenezcan. Como un/a estudiante no estará matriculado/a en distintos grupos,
     * no puede estar en la misma asignatura en distintos grupos con distinta nota.
     * La nota de las/los estudiantes será la nota de esa asignatura.
     * @param asignatura
     * @return
     */
    public ArrayList<Estudiante> getEstudiantes(Asignatura asignatura) {
        // TODO: getEstudiantes asignatura (42)
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        for (Grupo grupo : registro.keySet()) {
            if (registro.get(grupo).get(asignatura) != null) {
                estudiantes.addAll(registro.get(grupo).get(asignatura));
            }
        }
        if (estudiantes.isEmpty()) return null;
        return estudiantes;
    }

    /**
     * Devuelve un mapa ordenado por estudiante, asociado al grupo al que pertenecen,
     * en el que aparecen las/los estudiantes que tienen una nota mayor o igual en la asignatura indicada.
     * @param asignatura
     * @param nota
     * @return
     */
    public TreeMap<Estudiante,Grupo> getEstudiantesConNotaMayorIgualQue(Asignatura asignatura, double nota) {
        // TODO: getEstudiantesConNotaMayorIgualQue (43)
        TreeMap<Estudiante, Grupo> arbol = new TreeMap<>();
        ArrayList<Estudiante> estudiantesTotal = getEstudiantes(asignatura);
        for (Estudiante estudiante : estudiantesTotal) {
            if (estudiante.getNota() >= nota){
                for (Grupo grupo : registro.keySet()) {
                    if (existeEstudianteAsignaturaGrupo(estudiante,asignatura,grupo)){
                        arbol.put(estudiante,grupo);
                    }
                }
            }
        }
        return arbol;
    }

    //endregion

    //region distribuciones notas 51-52

    /**
     * Devuelve un mapa en el que las claves son valores del 0 al 10 y los valores el número de personas que en esa
     * asignatura en ese grupo han obtenido esa nota. El sumatorio de todos los valores debe ser el número de estudiantes
     * que hay en la lista de estudiantes de la asignatura del grupo. Por ejemplo, si nadie tiene un 0 en PROG en DAW1,
     * el valor asociado a la clave 0 será 0. Si hay 5 personas que han sacado una nota entre 8 y 9 (sin incluir el 9),
     * el valor asociado a la clave 8 será 5.
     *
     *     Opción: 51
     *     Nombre del grupo: DAW1
     *     Nombre de la asignatura: PROG
     *     |  0|  1|  2|  3|  4|  5|  6|  7|  8|  9| 10|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *     |  0|  2|  2|  3|  1|  1|  1|  1|  5|  3|  0|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *
     * @param asignatura
     * @param grupo
     * @return
     */
    public TreeMap<Integer,Integer> getDistribucionNotasAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO: getDistribucionNotasAsignaturaGrupo (51)
        TreeMap<Integer, Integer> mapa = new TreeMap<>();
        ArrayList<Estudiante> estudiantes = new ArrayList<>(getListaEstudiantesAsignaturaGrupo(asignatura,grupo));
        int[] notas = new int[10];
        for (Estudiante estudiante : estudiantes) {
            notas[(int)estudiante.getNota()]++;
        }
        for (int i = 0; i < notas.length; i++) {
            mapa.put(i, notas[i]);
        }
        return mapa;
    }

    /**
     * Devuelve un mapa en el que las claves son valores del 0 al 10 y los valores el número de personas que en esa
     * asignatura considerando todos los grupos han obtenido esa nota.
     *
     *     Opción: 52
     *     Nombre de la asignatura: PROG
     *     |  0|  1|  2|  3|  4|  5|  6|  7|  8|  9| 10|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *     |  5|  8|  5|  4|  4|  7|  1|  4| 13|  7|  1|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *
     * @param asignatura
     * @return
     */
    public TreeMap<Integer,Integer> getDistribucionNotasAsignatura(Asignatura asignatura) {
        // TODO: getDistribucionNotasAsignatura (52)
        TreeMap<Integer, Integer> mapa = new TreeMap<>();
        int[] notas = new int[13];
        for (Map.Entry<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> entry : registro.entrySet()) {
            Grupo g = entry.getKey();
            ArrayList<Estudiante> estudiantes = getListaEstudiantesAsignaturaGrupo(asignatura,g);
            if (estudiantes != null) {
                for (Estudiante estudiante : estudiantes) {
                    notas[(int) estudiante.getNota()]++;
                }
            }

        }
        for (int i = 0; i < notas.length; i++) {
            mapa.put(i, notas[i]);
        }
        return mapa;
    }

    private TreeMap<Integer,Integer> acumulaDistribucionesDeNotas(TreeMap<Integer,Integer> map1, TreeMap<Integer,Integer> map2) {
        return null;
    }

    //endregion

    //region info estudiante 61-62

    /**
     * Devuelve el grupo al que pertenece el estudiante o null. Un/a estudiante sólo pertenece a un grupo,
     * si se encuentra una coincidencia se puede devolver esa.
     *
     *     Opción: 61
     *     Nombre del/a estudiante: Samantha
     *     Apellidos del/a estudiante: Oag
     *     La/El estudiante pertenece al grupo DAW1
     *
     * @param nombre
     * @param apellidos
     * @return
     */
    public Grupo grupoDelEstudiante(String nombre, String apellidos) {
        // TODO: grupoDelEstudiante (61)
        for (Map.Entry<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> entry : registro.entrySet()) {
            Grupo g = entry.getKey();
            ArrayList<Estudiante> estudiantes = getEstudiantes(g);
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getNombre().equals(nombre) && estudiante.getApellidos().equals(apellidos)){
                    return g;
                }
            }
        }
        return null;
    }

    /**
     * Devuelve un mapa de asignaturas y notas obtenidas de un/a estudiante o null si no tiene.
     *
     *     Opción: 62
     *     Nombre del/a estudiante: Samantha
     *     Apellidos del/a estudiante: Oag
     *     LMGI : 5.89
     *     BADA : 5.45
     *     ING  : 6.74
     *     SIN  : 3.49
     *     ENDE : 8.90
     *     PROG : 3.70
     *
     * @param nombre
     * @param apellidos
     * @return
     */
    public HashMap<Asignatura, Double> notasEstudiante(String nombre, String apellidos) {
        // TODO: notasEstudiante (62)
        HashMap<Asignatura, Double> notas = new HashMap<>();
        Grupo g = grupoDelEstudiante(nombre, apellidos);
        HashSet<Asignatura> asignaturas = getAsignaturas(g);
        for (Asignatura asignatura : asignaturas) {
            ArrayList<Estudiante> estudiantes = registro.get(g).get(asignatura);
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getNombre().equals(nombre) && estudiante.getApellidos().equals(apellidos)){
                    notas.put(asignatura, estudiante.getNota());
                }
            }
        }
        if (notas.isEmpty()) return null;
        return notas;
    }

    //endregion

}

