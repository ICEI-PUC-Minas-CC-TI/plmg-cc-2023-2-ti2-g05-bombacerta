// Initialize and add the map
function initMap() {
    // The map, centered at the current location
    const map = new google.maps.Map(document.getElementById("map"), {
      zoom: 12
    });
  
    // Obter a localização atual
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        function (position) {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
  
          // Centralizar o mapa na localização atual
          map.setCenter(pos);
  
          // Adicionar um marcador na localização atual
          new google.maps.Marker({
            position: pos,
            map: map,
            title: 'Localização Atual'
          });
  
          // Adicionar um marcador em um posto 1 (local personalizado)
          const customLocation = { lat: -19.922797706609582, lng: -43.97943221074983 };
          const customMarker = new google.maps.Marker({
            position: customLocation,
            map: map,
            title: 'Local Personalizado',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
          const gasPriceABC = 4.87; // Preço da gasolina do posto ABC
          const alcoholPriceABC = 3.50; // Preço do álcool do posto ABC
  
          const infoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto Bretas<br>' +
              '<strong>GASOLINA:</strong> <span style="color: green;">R$' + gasPriceABC.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: red;">R$' + alcoholPriceABC.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
          customMarker.addListener('click', function () {
            infoWindow.open(map, customMarker);
          });
  
          // Adicionar outro marcador em uma localização específica (posto 2)
          const anotherLocation = { lat: -19.9215069157032, lng: -43.98231149715435 };
          const anotherMarker = new google.maps.Marker({
            position: anotherLocation,
            map: map,
            title: 'Segundo Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
          const gasPrice = 4.90; // Preço da gasolina
          const alcoholPrice = 3.30; // Preço do álcool
  
          // Verifica se o resultado da divisão é menor ou igual a 0.7
          if (gasPrice / alcoholPrice <= 0.7) {
            // Preço da gasolina em vermelho e do álcool em verde
            var gasColor = 'red';
            var alcoholColor = 'green';
          } else {
            // Preço da gasolina em verde e do álcool em vermelho
            var gasColor = 'green';
            var alcoholColor = 'red';
          }
  
          const anotherInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto Petrobras<br>' +
              '<strong>GASOLINA:</strong> <span style="color: ' + gasColor + ';">R$' + gasPrice.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: ' + alcoholColor + ';">R$' + alcoholPrice.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
          anotherMarker.addListener('click', function () {
            anotherInfoWindow.open(map, anotherMarker);
          });
  
          // Adicionar o terceiro marcador em uma localização específica (posto 3)
          const thirdLocation = { lat: -19.916394681189953, lng: -43.964994451816274 };
          const thirdMarker = new google.maps.Marker({
            position: thirdLocation,
            map: map,
            title: 'Terceiro Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
          const gasPricePQR = 5.00; // Preço da gasolina do posto PQR
          const alcoholPricePQR = 3.40; // Preço do álcool do posto PQR
  
          const thirdInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto King<br>' +
              '<strong>GASOLINA:</strong> <span style="color: red;">R$' + gasPricePQR.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: green;">R$' + alcoholPricePQR.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
          thirdMarker.addListener('click', function () {
            thirdInfoWindow.open(map, thirdMarker);
          });
  
          // Adicionar o quinto marcador em uma localização específica (posto 5)
          const fifthLocation = { lat: -19.9342674224452, lng: -44.022015284549106 };
          const fifthMarker = new google.maps.Marker({
            position: fifthLocation,
            map: map,
            title: 'Quinto Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
  
          const gasPriceBeijaFlor = 4.90; // Preço da gasolina do posto Beija Flor
          const alcoholPriceBeijaFlor = 3.30; // Preço do álcool do posto Beija Flor
  
          const fifthInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto Beija Flor<br>' +
              '<strong>GASOLINA:</strong> <span style="color: red;">R$' + gasPriceBeijaFlor.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: green;">R$' + alcoholPriceBeijaFlor.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
  
          fifthMarker.addListener('click', function () {
            fifthInfoWindow.open(map, fifthMarker);
          });
  
          // Adicionar o sexto marcador em uma localização específica (posto 6)
          const sixthLocation = { lat: -19.937355358797056, lng: -44.028764628884154 };
          const sixthMarker = new google.maps.Marker({
            position: sixthLocation,
            map: map,
            title: 'Sexto Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
  
          const gasPriceDEF = 4.95; // Preço da gasolina do posto DEF
          const alcoholPriceDEF = 3.35; // Preço do álcool do posto DEF
  
          const sixthInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto Jupiter<br>' +
              '<strong>GASOLINA:</strong> <span style="color: red;">R$' + gasPriceDEF.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: green;">R$' + alcoholPriceDEF.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
  
          sixthMarker.addListener('click', function () {
            sixthInfoWindow.open(map, sixthMarker);
          });
  
          // Adicionar o sétimo marcador em uma localização específica (posto 7)
          const seventhLocation = { lat: -19.93546961547217, lng: -44.03355257292824 };
          const seventhMarker = new google.maps.Marker({
            position: seventhLocation,
            map: map,
            title: 'Sétimo Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
  
          const gasPriceShell = 4.85; // Preço da gasolina do posto Shell
          const alcoholPriceShell = 3.25; // Preço do álcool do posto Shell
  
          const seventhInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto Shell<br>' +
              '<strong>GASOLINA:</strong> <span style="color: red;">R$' + gasPriceShell.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: green;">R$' + alcoholPriceShell.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
  
          seventhMarker.addListener('click', function () {
            seventhInfoWindow.open(map, seventhMarker);
          });
  
          // Adicionar o oitavo marcador em uma localização específica (posto 8)
          const eighthLocation = { lat: -19.93369899615736, lng: -44.00501381549639 };
          const eighthMarker = new google.maps.Marker({
            position: eighthLocation,
            map: map,
            title: 'Oitavo Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
  
          const gasPriceJKL = 4.75; // Preço da gasolina do posto JKL
          const alcoholPriceJKL = 3.15; // Preço do álcool do posto JKL
  
          const eighthInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto São João<br>' +
              '<strong>GASOLINA:</strong> <span style="color: red;">R$' + gasPriceJKL.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: green;">R$' + alcoholPriceJKL.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
  
          eighthMarker.addListener('click', function () {
            eighthInfoWindow.open(map, eighthMarker);
          });
  
          // Adicionar o nono marcador em uma localização específica (posto 9)
          const ninthLocation = { lat: -19.934369332546158, lng: -43.996370569933084 };
          const ninthMarker = new google.maps.Marker({
            position: ninthLocation,
            map: map,
            title: 'Nono Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
  
          const ninthGasPrice = 4.70; // Preço da gasolina do posto 9
          const ninthAlcoholPrice = 3.10; // Preço do álcool do posto 9
  
          const ninthInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto Águia<br>' +
              '<strong>GASOLINA:</strong> <span style="color: red;">R$' + ninthGasPrice.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: green;">R$' + ninthAlcoholPrice.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
          ninthMarker.addListener('click', function () {
            ninthInfoWindow.open(map, ninthMarker);
          });
  
  
  
          // Adicionar o quarto marcador em uma localização específica (posto 4)
          const fourthLocation = { lat: -19.922532125746507, lng: -43.97441736330393 };
          const fourthMarker = new google.maps.Marker({
            position: fourthLocation,
            map: map,
            title: 'Quarto Local',
            icon: {
              url: 'https://maps.google.com/mapfiles/ms/icons/gas.png',
              scaledSize: new google.maps.Size(32, 32)
            },
          });
  
          const fourthGasPrice = 4.80; // Preço da gasolina do posto XYZ
          const fourthAlcoholPrice = 3.20; // Preço do álcool do posto XYZ
  
          const fourthInfoWindow = new google.maps.InfoWindow({
            content: '<div>' +
              '<strong>Nome do Posto:</strong> Posto Alfa<br>' +
              '<strong>GASOLINA:</strong> <span style="color: red;">R$' + fourthGasPrice.toFixed(2) + '</span>' +
              ' <strong>|</strong> ' +
              '<strong>ÁLCOOL:</strong> <span style="color: green;">R$' + fourthAlcoholPrice.toFixed(2) + '</span><br>' +
              '</div>'
          });
  
  
          fourthMarker.addListener('click', function () {
            fourthInfoWindow.open(map, fourthMarker);
          });
  
  
  
  
        },
        function () {
          handleLocationError(true, map.getCenter());
        }
      );
    } else {
      // Navegador não suporta geolocalização
      handleLocationError(false, map.getCenter());
    }
  }
  
  function handleLocationError(browserHasGeolocation, pos) {
    const infoWindow = new google.maps.InfoWindow({
      position: pos,
      content: browserHasGeolocation
        ? "Erro: O serviço de geolocalização falhou."
        : "Erro: Seu navegador não suporta geolocalização."
    });
    infoWindow.open(map);
  }
  
  window.initMap = initMap;
  
  // Verificar permissão de geolocalização ao carregar a página
  document.addEventListener("DOMContentLoaded", function () {
    if ("geolocation" in navigator) {
      navigator.permissions
        .query({ name: "geolocation" })
        .then(function (result) {
          if (result.state === "granted") {
            initMap(); // Inicialize o mapa se a permissão for concedida
          } else if (result.state === "prompt") {
            showLocationPermissionAlert(); // Solicitar permissão se ainda não foi concedida
          } else {
            showLocationPermissionDeniedAlert(); // Permissão de acesso à localização negada
          }
        });
    } else {
      // O navegador não suporta a API de geolocalização
      showLocationNotSupportedAlert();
    }
  });
  
  function showLocationPermissionAlert() {
    alert("Por favor, conceda acesso à sua localização para visualizar o mapa.");
  }
  
  function showLocationPermissionDeniedAlert() {
    alert("O acesso à sua localização foi negado. Alguns recursos podem não estar disponíveis.");
  }
  
  function showLocationNotSupportedAlert() {
    alert(
      "Seu navegador não suporta geolocalização. Alguns recursos podem não estar disponíveis."
    );
  }
  
  
  
  
  // Obtém os elementos HTML relevantes
  const loginLink = document.getElementById('loginLink');
  const overlay = document.getElementById('overlay');
  const loginForm = document.getElementById('loginForm');
  const usernameInput = document.getElementById('username');
  const passwordInput = document.getElementById('password');
  const createAccountButton = document.getElementById('createAccountButton');
  
  // Adiciona o evento de clique no link de login
  loginLink.addEventListener('click', function (event) {
    event.preventDefault(); // Previne o comportamento padrão do link
    overlay.style.display = 'flex'; // Exibe o overlay de login
  });
  
  // Adiciona o evento de envio do formulário de login
  // loginForm.addEventListener('submit', function (event) {
  //   event.preventDefault(); // Previne o comportamento padrão do formulário
  
  //   // Valida o login com base nas informações armazenadas
  //   const username = usernameInput.value;
  //   const password = passwordInput.value;
  
  //   const storedAccountData = JSON.parse(localStorage.getItem('accountData'));
  //   if (storedAccountData && storedAccountData.username === username && storedAccountData.password === password) {
  //       // Redireciona o usuário para a página do usuário
  //       window.location.href = 'login.html';
  //   } else {
  //       alert('Usuário ou senha inválidos. Tente novamente.');
  //   }
  // });
  
  
  // Adiciona o evento de clique no botão "Criar Conta"
  // createAccountButton.addEventListener('click', function (event) {
  //     event.preventDefault(); // Previne o comportamento padrão do link
  
  //     const newUsername = usernameInput.value;
  //     const newPassword = passwordInput.value;
  
  //     // Armazena as informações da nova conta no LocalStorage
  //     const accountData = {
  //         username: newUsername,
  //         password: newPassword
  //     };
  //     localStorage.setItem('accountData', JSON.stringify(accountData));
  
  //     alert('Conta criada com sucesso! Faça login com as informações fornecidas.');
  // });
  
  
  
  // Fecha o pop-up de login ao clicar fora dele
  overlay.addEventListener('click', function (event) {
    if (event.target === overlay) {
      overlay.style.display = 'none';
    }
  });