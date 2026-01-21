# Push Spring DI Project to GitHub

## Step-by-Step Guide

### 1. Create GitHub Repository
- Go to https://github.com/new
- Repository name: `spring-dependency-injection` (or your choice)
- Description: "Spring Framework Dependency Injection Demo - Constructor & Setter Injection with XML and Annotation Configuration"
- Choose: Public or Private
- Click "Create repository"

### 2. Configure Git User (First Time Only)
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### 3. Add Remote Repository
Replace `yourusername` and `your-repo` with your actual values:

```bash
cd c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo
git remote add origin https://github.com/yourusername/spring-dependency-injection.git
```

### 4. Rename Branch to Main (if needed)
```bash
git branch -M main
```

### 5. Push to GitHub
```bash
git push -u origin main
```

### 6. Verify on GitHub
- Go to your repository on GitHub
- You should see all your files and the initial commit message

## Alternative: Using SSH

### Setup SSH Key (One-time setup)
```bash
# Generate SSH key
ssh-keygen -t ed25519 -C "your.email@example.com"

# Start SSH agent and add key
# Windows: Use Git Bash
eval $(ssh-agent -s)
ssh-add ~/.ssh/id_ed25519

# Copy public key to GitHub
# Go to Settings → SSH and GPG keys → New SSH key
# Paste content of ~/.ssh/id_ed25519.pub
```

### Push using SSH
```bash
git remote remove origin
git remote add origin git@github.com:yourusername/spring-dependency-injection.git
git push -u origin main
```

## Subsequent Updates

### After Making Changes
```bash
# Check status
git status

# Stage changes
git add .

# Commit with meaningful message
git commit -m "Update: Add unit tests for Student class"

# Push to GitHub
git push origin main
```

## GitHub Actions (Optional CI/CD)

Create `.github/workflows/maven.yml`:

```yaml
name: Maven Build

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'adopt'
    - name: Build with Maven
      run: mvn clean compile
```

## Repository Content

Your GitHub repository will contain:

```
📁 spring-dependency-injection/
├── 📄 README.md                    # Original project README
├── 📄 SPRING_DI_README.md         # Detailed DI documentation
├── 📄 SETUP_GUIDE.md              # Setup and installation guide
├── 📄 pom.xml                     # Maven configuration
├── 📄 .gitignore                  # Git ignore patterns
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/
│       │   └── 📁 com/example/
│       │       ├── 📄 Student.java
│       │       ├── 📄 ApplicationConfig.java
│       │       ├── 📄 MainXML.java
│       │       └── 📄 MainAnnotation.java
│       └── 📁 resources/
│           └── 📄 applicationContext.xml
└── 📁 .github/                    # (Optional) GitHub workflows
    └── 📁 workflows/
        └── 📄 maven.yml           # CI/CD pipeline
```

## Useful Git Commands

```bash
# View commit history
git log

# View specific commit details
git show <commit-hash>

# Create a new branch
git checkout -b feature/new-feature

# Switch branches
git checkout main

# Merge branch into main
git checkout main
git merge feature/new-feature

# Delete branch
git branch -d feature/new-feature

# Tag a release
git tag v1.0.0
git push origin v1.0.0

# View remote URLs
git remote -v

# Update remote tracking
git fetch origin
```

## Collaboration

### Clone Your Repository
```bash
git clone https://github.com/yourusername/spring-dependency-injection.git
cd spring-dependency-injection
```

### Create Pull Request
1. Fork repository
2. Create feature branch: `git checkout -b feature/enhancement`
3. Make changes and commit
4. Push branch: `git push origin feature/enhancement`
5. Open Pull Request on GitHub

## Security & Best Practices

### Protect Sensitive Information
- Never commit `application-prod.yml` or secret configurations
- Use `.gitignore` for sensitive files (already configured)
- For secrets, use GitHub Secrets for CI/CD

### Keep Repository Clean
```bash
# Remove untracked files
git clean -fd

# Revert uncommitted changes
git checkout -- .

# Undo last commit (keep changes)
git reset --soft HEAD~1
```

## Troubleshooting GitHub Push

### Authentication Failed
```bash
# Update credentials
git credential-manager delete <hostname>
# Then try push again (will prompt for credentials)
```

### Large Files
```bash
# Check file size
git ls-tree -r -t -l --full-tree HEAD | sort -n -k4

# Use Git LFS for large files
git lfs install
git lfs track "*.jar"
git add .gitattributes
git add your-large-file.jar
git commit -m "Add large file with Git LFS"
git push origin main
```

## Next Steps
1. Share repository URL with team members
2. Create issue templates for bug reports and features
3. Set up branch protection rules (Require PR reviews)
4. Configure GitHub Pages for project documentation
5. Add topics/tags to repository for discoverability

## Support Resources
- GitHub Docs: https://docs.github.com
- Git Documentation: https://git-scm.com/doc
- GitHub Community: https://github.community
