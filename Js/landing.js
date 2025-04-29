document.addEventListener("DOMContentLoaded", function () {
  // Navbar + logo logic (already in your code)
  const navbar = document.querySelector(".navbar");
  const logo = document.querySelector(".logo-img");

  window.addEventListener("scroll", function () {
    if (window.scrollY > 50) {
      navbar.classList.add("scrolled");
      logo.classList.add("hide-on-scroll");
    } else {
      navbar.classList.remove("scrolled");
      logo.classList.remove("hide-on-scroll");
    }

    // Reveal effect for elements with .reveal
    const reveals = document.querySelectorAll(".reveal");
    reveals.forEach(function (el) {
      const windowHeight = window.innerHeight;
      const revealTop = el.getBoundingClientRect().top;
      const revealPoint = 150;

      if (revealTop < windowHeight - revealPoint) {
        el.classList.add("active");
      } else {
        el.classList.remove("active");
      }
    });
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const sections = document.querySelectorAll("section");
  const navLinks = document.querySelectorAll(".navbar-nav .nav-link");

  function setActiveLink() {
    let currentSection = "";

    sections.forEach(section => {
      const sectionTop = section.offsetTop - 100;
      if (window.scrollY >= sectionTop) {
        currentSection = section.getAttribute("id");
      }
    });

    navLinks.forEach(link => {
      link.classList.remove("active");
      if (link.getAttribute("href") === `#${currentSection}`) {
        link.classList.add("active");
      }
    });
  }

  window.addEventListener("scroll", setActiveLink);
});