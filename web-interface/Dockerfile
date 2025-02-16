FROM node:18
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm ci
COPY . .
EXPOSE 4200
CMD ["npm", "run", "start", "--", "--proxy-config", "src/proxy.conf.json", "--host", "0.0.0.0"]
