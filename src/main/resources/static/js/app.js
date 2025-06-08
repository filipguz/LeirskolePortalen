<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function toggleTheme() {
    const body = document.body;
    const icon = document.getElementById('themeIcon');
    const isDark = body.classList.toggle('dark');
    body.classList.toggle('light', !isDark);
    icon.className = isDark ? 'fas fa-sun' : 'fas fa-moon';
    localStorage.setItem('theme', isDark ? 'dark' : 'light');
  }

  window.addEventListener('DOMContentLoaded', () => {
    const theme = localStorage.getItem('theme') || 'light';
    document.body.classList.remove('light', 'dark');
    document.body.classList.add(theme);
    document.getElementById('themeIcon').className = theme === 'dark' ? 'fas fa-sun' : 'fas fa-moon';

    const allergiEl = document.querySelector('[th\\:text="${allergiCount}"]');
    const allergiAntall = parseInt(allergiEl?.textContent || "0");
    if (allergiAntall > 0) {
      const toast = new bootstrap.Toast(document.getElementById('allergiToast'));
      toast.show();
    }
  });
</script>