package com.caesar_84.dsi_docs_reg.domain.model;

public enum Department {
    OPERACIONES ("Operaciones"),
    SIAHO ("SIAHO"),
    RRHH ("Recursos Humanos"),
    FINANZAS ("Finanzas"),
    SERVICIOS_GENERALES ("Servicios Generales"),
    DSI ("DSI"),
    AIT ("AIT"),
    PLANIFICACION ("Planificación"),
    PROCURA ("Procura"),
    LEGAL ("Legal"),
    ASESOR_COMERCIAL ("Asesor Comercial"),
    PRESIDENCIA ("Presidencia"),
    GERENCIA_GENERAL ("Gerencia General");

    private final String name;

    Department(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }


    @Override
    public String toString() {
        return this.name;
    }
}
