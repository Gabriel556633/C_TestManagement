package com.tcs.ms_test_management.domain.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "RPT_REPLICA_CLIENTES_FINAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RptReplicaClientesFinal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String identificacionCliente;

    @Column(name = "ESTADO_CLIENTE")
    private String estadoCliente;

    @Column(name = "SEGMENTO_CLIENTE")
    private String segmentoCliente;

    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDOS")
    private String apellidos;

    @Column(name = "NACIONALIDAD")
    private String nacionalidad;

    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "NUMERO_SERVICIO")
    private String numeroServicio;

    @Column(name = "CUENTA_FACTURACION_LEGADO")
    private String cuentaFacturacionLegado;

    @Column(name = "SUBCRIPCION")
    private String subcripcion;

    @Column(name = "FORMA_PAGO")
    private String formaPago;

    @Column(name = "TECNOLOGIA")
    private String tecnologia;

    @Column(name = "ESTADO_SERVICIO")
    private String estadoServicio;

    @Column(name = "PRODUCTO")
    private String producto;

    @Column(name = "FECHA_ACTIVACION")
    @Temporal(TemporalType.DATE)
    private Date fechaActivacion;

    @Column(name = "SUCURSAL")
    private String sucursal;

    @Column(name = "DESCRIPCION_SUCURSAL")
    private String descripcionSucursal;

    @Column(name = "REGION")
    private String region;

    @Column(name = "LOCATIONCODE")
    private String locationCode;

    @Column(name = "PROVINCIA")
    private String provincia;

    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "SUPRODUCTO")
    private String suProducto;

    @Column(name = "DESCRIPCION_SUBPRODUCTO")
    private String descripcionSubProducto;

    @Column(name = "ACCOUNTID")
    private String accountId;

    @Column(name = "CICLO_FACTURACION")
    private String cicloFacturacion;

    @Column(name = "ID_PLAN")
    private String idPlan;

    @Column(name = "DESCRIPCION_PLAN")
    private String descripcionPlan;

    @Column(name = "CRM")
    private String crm;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "TELEFONO_CASA")
    private String telefonoCasa;

    @Column(name = "TELEFONO_OFICINA")
    private String telefonoOficina;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "DISTRITO")
    private String distrito;

    @Column(name = "DIRECCION_DOMICILIO")
    private String direccionDomicilio;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "CODIGO_ZIP")
    private String codigoZip;

    @Column(name = "SUBSCRIPTIONID")
    private String subscriptionId;

    @Column(name = "NEUTRALGROUND")
    private String neutralGround;

    @Column(name = "PEDESTALNUMBER")
    private String pedestalNumber;

    @Column(name = "PORT")
    private String port;

    @Column(name = "TAP")
    private String tap;

    @Column(name = "AMPLIFIERPORT")
    private String amplifierPort;

    @Column(name = "BUILDING")
    private String building;

    @Column(name = "NODE")
    private String node;

    @Column(name = "HUB")
    private String hub;

    @Column(name = "AMPLIFIER")
    private String amplifier;

    @Column(name = "TAPPORT")
    private String tapPort;

    @Column(name = "OLT")
    private String olt;

    @Column(name = "NAPCODE")
    private String napCode;

    @Column(name = "PRECINTOCLIENT")
    private String precintoClient;

    @Column(name = "SLOT")
    private String slot;

    @Column(name = "NEUTRALPHASE")
    private String neutralPhase;

    @Column(name = "GROUNDPHASE")
    private String groundPhase;

    @Column(name = "NAPPORT")
    private String napPort;

    @Column(name = "REGENERATEDZONE")
    private String regeneratedZone;

    @Column(name = "FRAME")
    private String frame;

    @Column(name = "DEVICEOFFERNAME1")
    private String deviceOfferName1;

    @Column(name = "DEVICESERIALNUMBER1")
    private String deviceSerialNumber1;

    @Column(name = "DEVICEOFFERID1")
    private String deviceOfferId1;

    @Column(name = "DEVICEOFFERNAME2")
    private String deviceOfferName2;

    @Column(name = "DEVICESERIALNUMBER2")
    private String deviceSerialNumber2;

    @Column(name = "DEVICEOFFERID2")
    private String deviceOfferId2;

    @Column(name = "DEVICEOFFERNAME3")
    private String deviceOfferName3;

    @Column(name = "DEVICESERIALNUMBER3")
    private String deviceSerialNumber3;

    @Column(name = "DEVICEOFFERID3")
    private String deviceOfferId3;

    @Column(name = "DEVICEOFFERNAME4")
    private String deviceOfferName4;

    @Column(name = "DEVICESERIALNUMBER4")
    private String deviceSerialNumber4;

    @Column(name = "DEVICEOFFERID4")
    private String deviceOfferId4;

    @Column(name = "SERIALNUMBER")
    private String serialNumber;

    @Column(name = "VENDOR")
    private String vendor;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "MACADDRESSCM")
    private String macAddressCM;

    @Column(name = "MACADDRESSMTA")
    private String macAddressMTA;

    @Column(name = "FIRMWARE_VERSIONCM")
    private String firmwareVersionCM;

    @Column(name = "FIRMWARE_VERSIONMTA")
    private String firmwareVersionMTA;

    @Column(name = "HOSTNAME_ROUTER")
    private String hostnameRouter;

    @Column(name = "IP_ROUTER")
    private String ipRouter;

    @Column(name = "HOSTNAME_SWITCHAGG")
    private String hostnameSwitchAgg;

    @Column(name = "IP_SWITCHAGG")
    private String ipSwitchAgg;

    @Column(name = "MAC_CM")
    private String macCM;

    @Column(name = "ID_CM")
    private String idCM;

    @Column(name = "INTERFACE_AGG_TOROUTER")
    private String interfaceAggToRouter;

    @Column(name = "MAC_AGG")
    private String macAgg;

    @Column(name = "INTERFACE_ACC_TOAGG")
    private String interfaceAccToAgg;

    @Column(name = "HOSTNAME_ACC")
    private String hostnameAcc;

    @Column(name = "IP_ACC")
    private String ipAcc;

    @Column(name = "MAC_ACC")
    private String macAcc;

    @Column(name = "INTERFACE_ACC_TORMD")
    private String interfaceAccToRmd;

    @Column(name = "HOSTNAME_RMD_CMTS")
    private String hostnameRmdCmts;

    @Column(name = "IP_RMD_CMTS")
    private String ipRmdCmts;

    @Column(name = "MAC_RMD_CMTS")
    private String macRmdCmts;

    @Column(name = "NOMBRE_NODO")
    private String nombreNodo;

    @Column(name = "MAC_ONT")
    private String macOnt;

    @Column(name = "FECHA_ONT")
    @Temporal(TemporalType.DATE)
    private Date fechaOnt;

    @Column(name = "INTERFAZOLT_TOONET")
    private String interfazOltToOnet;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "HOSTNAME_OLT")
    private String hostnameOlt;

    @Column(name = "IP_OLT")
    private String ipOlt;

    @Column(name = "INTERFACEACC_TOOLT")
    private String interfaceAccToOlt;

    @Column(name = "IP_SWITCHACC")
    private String ipSwitchAcc;

    @Column(name = "MAC_SWACC")
    private String macSwAcc;

    @Column(name = "INTERFACES_TOSWAGG")
    private String interfacesToSwAgg;

    @Column(name = "INTERFACEROUTER_TOAGG")
    private String interfaceRouterToAgg;

    @Column(name = "FECHA_ROUTER")
    @Temporal(TemporalType.DATE)
    private Date fechaRouter;

    @Column(name = "FECHA_CORTE")
    @Temporal(TemporalType.DATE)
    private Date fechaCorte;

    @Column(name = "FECHA_EJECUCION")
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucion;

    // Getters and Setters
}