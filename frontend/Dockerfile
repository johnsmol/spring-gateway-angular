FROM node:slim as node
RUN mkdir /project
WORKDIR /project

COPY . .

RUN npm install -g @angular/cli
RUN yarn install
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200", "--disable-host-check", "--proxy-config", "proxy.conf.json"]
