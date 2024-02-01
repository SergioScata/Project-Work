const chatbotToggler = document.querySelector(".chatbot-toggler");
const closeBtn = document.querySelector(".close-btn");
const chatbox = document.querySelector(".chatbox");
const chatInput = document.querySelector(".chat-input textarea");
const sendChatBtn = document.querySelector(".chat-input span");

let userMessage = null; // Variable to store user's message
const API_KEY = ""; // Paste your API key here
const inputInitHeight = chatInput.scrollHeight;

const createChatLi = (message, className) => {
    // Create a chat <li> element with passed message and className
    const chatLi = document.createElement("li");
    chatLi.classList.add("chat", `${className}`);
    let chatContent = className === "outgoing" ? `<p></p>` : `<span class="material-symbols-outlined"></span><p></p>`;
    chatLi.innerHTML = chatContent;
    chatLi.querySelector("p").textContent = message;
    return chatLi; // return chat <li> element
}

const generateResponse = (chatElement) => {
    const API_URL = "https://api.openai.com/v1/chat/completions";
    //const API_URL = "http://localhost:1234/v1/chat/completions";
    const messageElement = chatElement.querySelector("p");

    // Define the properties and message for the API request
    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${API_KEY}`
        },
        body: JSON.stringify({
            model: "gpt-3.5-turbo",
            messages: [
            { role: "system", content: "Sei un assistente per i clienti del sito del museo \"The Museum\", situato nel cuore dell'antica Roma. Fondato nel 1925 con una struttura neoclassica progettata da Elena Birkenahuer, il museo ospita una ricca collezione di arte classica, antichità romane e sculture greche. Serve come portale attraverso i secoli, esplorando le connessioni tra epoche e movimenti artistici, e si impegna a preservare il patrimonio culturale per le future generazioni. Il sito del museo è stato creato da Sergio Scatamacchia, Riccardo Pollice, Alessandro Dodaro, Matteo Farinazzo e Francesco Barretta. L'assistente guiderà gli utenti verso informazioni sul museo, registrazioni, accesso allo Shop (che richiede registrazione e login) e navigazione nel sito. La sezione Shop è accessibile solo agli utenti registrati e loggati, con un link diretto fornito. Per registrarsi o accedere, gli utenti possono trovare le opzioni nella navbar in alto a destra, cliccando sull'icona dell'omino bianco che apre una sidebar con le opzioni di Login, Register e Logout. Dopo l'accesso, nella sidebar, sono disponibili le sezioni \"I tuoi Acquisti\" e \"Il tuo Profilo\". Le uniche sezioni che ci sono sul sito quindi sono: la Home, lo Shop, Register, Login, Logout, I tuoi acquisti, e Il tuo Profilo. Se l'utente chiede info su una di queste sezioni, tu darai le informazioni necessarie e in più invierai il link per entrare nelle varie pagine/sezioni del sito. Per la Home: http://localhost:8080/, per lo Shop: http://localhost:8080/shop, per il login: http://localhost:8080/login, per il Register: http://localhost:8080/user/register, per i tuoi Acquisti: http://localhost:8080/user/acquisti, per il tuo Profilo: http://localhost:8080/user"},
            { role: "user", content: userMessage }],
        })
    }

    // Send POST request to API, get response and set the reponse as paragraph text
    fetch(API_URL, requestOptions).then(res => res.json()).then(data => {
        messageElement.textContent = data.choices[0].message.content.trim();
    }).catch(() => {
        messageElement.classList.add("error");
        messageElement.textContent = "Oops! Something went wrong. Please try again.";
    }).finally(() => chatbox.scrollTo(0, chatbox.scrollHeight));
}

const handleChat = () => {
    userMessage = chatInput.value.trim(); // Get user entered message and remove extra whitespace
    if (!userMessage) return;

    // Clear the input textarea and set its height to default
    chatInput.value = "";
    chatInput.style.height = `${inputInitHeight}px`;

    // Append the user's message to the chatbox
    chatbox.appendChild(createChatLi(userMessage, "outgoing"));
    chatbox.scrollTo(0, chatbox.scrollHeight);

    setTimeout(() => {
        // Display "Thinking..." message while waiting for the response
        const incomingChatLi = createChatLi("Thinking...", "incoming");
        chatbox.appendChild(incomingChatLi);
        chatbox.scrollTo(0, chatbox.scrollHeight);
        generateResponse(incomingChatLi);
    }, 600);
}

chatInput.addEventListener("input", () => {
    // Adjust the height of the input textarea based on its content
    chatInput.style.height = `${inputInitHeight}px`;
    chatInput.style.height = `${chatInput.scrollHeight}px`;
});

chatInput.addEventListener("keydown", (e) => {
    // If Enter key is pressed without Shift key and the window 
    // width is greater than 800px, handle the chat
    if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
        e.preventDefault();
        handleChat();
    }
});

sendChatBtn.addEventListener("click", handleChat);
closeBtn.addEventListener("click", () => document.body.classList.remove("show-chatbot"));
chatbotToggler.addEventListener("click", () => document.body.classList.toggle("show-chatbot"));


//sidebar script

function openNav() {
  document.getElementById("mySidebar").style.width = "250px";
  document.body.style.marginRight = "250px";
}

function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.body.style.marginRight= "0";
}

window.addEventListener('scroll', function() {
        const statua = document.getElementById('statua');
        const scrollPosition = window.pageYOffset;
        const speed = -0.40;

        statua.style.transform = 'translateY(' + (-scrollPosition * speed) + 'px)';
    });

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
    document.getElementById("big_title").style.fontSize = "1.0em";
  } else {
    document.getElementById("big_title").style.fontSize = "1.3em";
  }

  if (document.body.scrollTop > 350 || document.documentElement.scrollTop > 350) {
      document.getElementById("big_title").style.left = "-100em";
    } else {
      document.getElementById("big_title").style.left = "-5em";
    }

  if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
          document.getElementById("big_title").style.top = "5em";
        } else {
          document.getElementById("big_title").style.top = "-5em";
        }
}



const prev = document.querySelector("#prev");
const next = document.querySelector("#next");

let carouselVp = document.querySelector("#carousel-vp");

let cCarouselInner = document.querySelector("#cCarousel-inner");
let carouselInnerWidth = cCarouselInner.getBoundingClientRect().width;

let leftValue = 0;

// Variable used to set the carousel movement value (card's width + gap)
const totalMovementSize =
  parseFloat(
    document.querySelector(".cCarousel-item").getBoundingClientRect().width,
    10
  ) +
  parseFloat(
    window.getComputedStyle(cCarouselInner).getPropertyValue("gap"),
    10
  );

prev.addEventListener("click", () => {
  if (!leftValue == 0) {
    leftValue -= -totalMovementSize;
    cCarouselInner.style.left = leftValue + "px";
  }
});

next.addEventListener("click", () => {
  const carouselVpWidth = carouselVp.getBoundingClientRect().width;
  if (carouselInnerWidth - Math.abs(leftValue) > carouselVpWidth) {
    leftValue -= totalMovementSize;
    cCarouselInner.style.left = leftValue + "px";
  } else {  leftValue = 0;
  cCarouselInner.style.left = "0" + "px";
  }
});

const mediaQuery510 = window.matchMedia("(max-width: 510px)");
const mediaQuery770 = window.matchMedia("(max-width: 770px)");

mediaQuery510.addEventListener("change", mediaManagement);
mediaQuery770.addEventListener("change", mediaManagement);

let oldViewportWidth = window.innerWidth;

function mediaManagement() {
  const newViewportWidth = window.innerWidth;

  if (leftValue <= -totalMovementSize && oldViewportWidth < newViewportWidth) {
    leftValue += totalMovementSize;
    cCarouselInner.style.left = leftValue + "px";
    oldViewportWidth = newViewportWidth;
  } else if (
    leftValue <= -totalMovementSize &&
    oldViewportWidth > newViewportWidth
  ) {
    leftValue -= totalMovementSize;
    cCarouselInner.style.left = leftValue + "px";
    oldViewportWidth = newViewportWidth;
  }
}




