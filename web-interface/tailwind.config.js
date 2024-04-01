/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
      "./src/**/*.{html,ts}"
  ],
  theme: {
    extend: {
        colors: {
            bgPrimary: '#1e1d1d',
            bgSecondary: '#000000',
            primary: '#ffffff',
            secondary: '#a8a8a8'
        }
    },
  },
  plugins: [],
}

