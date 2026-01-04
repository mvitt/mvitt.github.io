/**
 * Portfolio Main Script
 * Handles animations, navigation, and interactions.
 */

document.addEventListener('DOMContentLoaded', () => {
    // Register GSAP Plugin
    gsap.registerPlugin(ScrollTrigger);
    
    // Check for reduced motion
    const prefersReducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)').matches;
    
    initLoader();
    initNav();
    if (!prefersReducedMotion) {
        initAnimations();
    } else {
        // Instant reveal for reduced motion
        document.body.classList.remove('loading');
        gsap.set('.section', { opacity: 1, y: 0 });
    }
});

/**
 * Handle Loading Screen and Hero Entrance
 */
function initLoader() {
    const tl = gsap.timeline({
        onComplete: () => {
            document.body.classList.remove('loading');
        }
    });

    // Animate loader content in
    tl.to('.loader-content', { 
        input: 0, 
        duration: 0.8, 
        opacity: 1, 
        y: 0, 
        ease: 'power2.out' 
    })
    // Hold briefly
    .to('.loader-content', { 
        duration: 0.8, 
        opacity: 0, 
        y: -20, 
        ease: 'power2.in', 
        delay: 0.5 
    })
    // Slide loader curtain away
    .to('.loader', { 
        top: '-100%', 
        duration: 1, 
        ease: 'power4.inOut' 
    }, '-=0.2')
    // Reveal Hero Content - Staggered
    .from('.hero-subtitle', { 
        opacity: 0, 
        y: 20, 
        duration: 0.8, 
        ease: 'power2.out' 
    }, '-=0.5')
    .from('.hero-title', { 
        opacity: 0, 
        y: 30, 
        duration: 0.8, 
        ease: 'power2.out' 
    }, '-=0.6')
    .from('.hero-role', { 
        opacity: 0, 
        y: 20, 
        duration: 0.8, 
        ease: 'power2.out' 
    }, '-=0.6')
    .from('.hero-text', { 
        opacity: 0, 
        y: 20, 
        duration: 0.8, 
        ease: 'power2.out' 
    }, '-=0.6')
    .from('.hero-actions', { 
        opacity: 0, 
        y: 20, 
        duration: 0.8, 
        ease: 'power2.out' 
    }, '-=0.6')
    ;
}

/**
 * Navigation Logic (Mobile Toggle + Scroll Spy)
 */
function initNav() {
    const nav = document.querySelector('.navbar');
    const menuToggle = document.querySelector('.menu-toggle');
    const navLinks = document.querySelector('.nav-links');
    const links = document.querySelectorAll('.nav-link');
    
    // Sticky Nav Effect
    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            nav.classList.add('scrolled');
        } else {
            nav.classList.remove('scrolled');
        }
    });
    
    // Mobile Menu Toggle
    menuToggle.addEventListener('click', () => {
        const isExpanded = menuToggle.getAttribute('aria-expanded') === 'true';
        menuToggle.setAttribute('aria-expanded', !isExpanded);
        menuToggle.classList.toggle('active');
        navLinks.classList.toggle('active');
    });
    
    // Close menu when link clicked
    links.forEach(link => {
        link.addEventListener('click', () => {
            menuToggle.setAttribute('aria-expanded', 'false');
            menuToggle.classList.remove('active');
            navLinks.classList.remove('active');
        });
    });
    
    // Active Link Highlighting (Scroll Spy)
    const sections = document.querySelectorAll('section');
    window.addEventListener('scroll', () => {
        let current = '';
        const scrollY = window.scrollY;
        
        sections.forEach(section => {
            const sectionTop = section.offsetTop;
            const sectionHeight = section.clientHeight;
            if (scrollY >= (sectionTop - 200)) {
                current = section.getAttribute('id');
            }
        });
        
        links.forEach(link => {
            link.classList.remove('active');
            if (link.getAttribute('href').includes(current)) {
                if (current !== '') {
                    link.classList.add('active');
                }
            }
        });
    });
}

/**
 * Scroll Animations using GSAP ScrollTrigger
 */
function initAnimations() {
    // General Section Reveal
    gsap.utils.toArray('.section').forEach(section => {
        gsap.to(section, {
            scrollTrigger: {
                trigger: section,
                start: 'top 80%',
                toggleActions: 'play none none reverse'
            },
            opacity: 1,
            y: 0,
            duration: 0.8,
            ease: 'power2.out'
        });
    });
    
    // Staggered List Items (Education, Skills, Projects)
    const staggers = [
        '.edu-card', 
        '.skill-card', 
        '.project-card', 
        '.tag-item'
    ];
    
    staggers.forEach(selector => {
        // Only if elements exist
        const elements = document.querySelectorAll(selector);
        if (elements.length > 0) {
            gsap.from(elements, {
                scrollTrigger: {
                    trigger: elements[0].closest('.section') || selector,
                    start: 'top 90%' // Trigger earlier
                },
                y: 30,
                opacity: 0,
                duration: 0.6,
                stagger: 0.1,
                ease: 'power2.out',
                clearProps: 'all' // Clear props after animation to prevent stuck states
            });
        }
    });
}
