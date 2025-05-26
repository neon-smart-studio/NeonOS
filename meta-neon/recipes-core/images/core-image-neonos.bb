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
"
