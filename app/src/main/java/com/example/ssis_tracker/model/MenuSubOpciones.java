package com.example.ssis_tracker.model;

public class MenuSubOpciones {

        private int id_sub_opcion;
        private String id;
        private String sub_opcion;
        private boolean habilitado;

        public String getId() {
        return id;
    }
        public void setId(String id) {
        this.id = id;
    }
        public int getId_sub_opcion() {
            return id_sub_opcion;
        }
        public void setId_sub_opcion(int id_sub_opcion) {
            this.id_sub_opcion = id_sub_opcion;
        }
        public String getSub_opcion() {
            return sub_opcion;
        }
        public void setSub_opcion(String sub_opcion) {
            this.sub_opcion = sub_opcion;
        }
        public boolean isHabilitado() {
            return habilitado;
        }
        public void setHabilitado(boolean habilitado) {
            this.habilitado = habilitado;
        }
}
