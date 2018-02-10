do_deploy_append() {
cat <<EOL >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
dtoverlay=dwc2
hdmi_group=2
hdmi_mode=4
EOL
}

