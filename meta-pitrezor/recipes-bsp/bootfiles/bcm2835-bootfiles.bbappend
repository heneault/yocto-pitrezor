do_deploy() {
    install -d ${DEPLOYDIR}/${PN}

    for i in ${S}/*.bin ; do
        cp $i ${DEPLOYDIR}/${PN}
    done

    cp ${S}/start.elf ${DEPLOYDIR}/${PN}
    cp ${S}/fixup.dat ${DEPLOYDIR}/${PN}

    # Add stamp in deploy directory
    touch ${DEPLOYDIR}/${PN}/${PN}-${PV}.stamp
}

