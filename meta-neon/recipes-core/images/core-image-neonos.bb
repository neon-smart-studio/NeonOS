DESCRIPTION = "NeonOS base image with SSH, WiFi, systemd, and debug tools"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL += " \
    bash \
    openssh \
    dnsmasq \
    hostapd \
    iproute2 \
    iputils \
    curl \
    sudo \
    htop \
    iw \
"

ROOTFS_POSTPROCESS_COMMAND += "neon_ap_install;"

neon_ap_install() {
    install -d ${IMAGE_ROOTFS}/usr/bin
    install -m 0755 ${THISDIR}/files/ap-start.sh ${IMAGE_ROOTFS}/usr/bin/ap-start.sh

    install -d ${IMAGE_ROOTFS}/etc/systemd/system
    install -m 0644 ${THISDIR}/files/ap-mode.service ${IMAGE_ROOTFS}/etc/systemd/system/ap-mode.service
    install -d ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants
    ln -sf ../ap-mode.service ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants/ap-mode.service

    install -d ${IMAGE_ROOTFS}/etc/hostapd
    install -m 0644 ${THISDIR}/files/hostapd.conf ${IMAGE_ROOTFS}/etc/hostapd/hostapd.conf

    install -d ${IMAGE_ROOTFS}/etc/systemd/network
    install -m 0644 ${THISDIR}/files/wlan0.network ${IMAGE_ROOTFS}/etc/systemd/network/wlan0.network

    install -m 0644 ${THISDIR}/files/dnsmasq.conf ${IMAGE_ROOTFS}/etc/dnsmasq.conf
}