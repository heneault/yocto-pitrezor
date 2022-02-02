FROM debian:buster

RUN apt-get update && \
	apt-get install -y gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat cpio python python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping locales liblz4-tool zstd

RUN useradd -ms /bin/bash -p build build --uid 30000

RUN echo "en_US.UTF-8 UTF-8" > /etc/locale.gen && locale-gen
ENV LANG en_US.utf8

USER build
WORKDIR /home/build
