const cors = require('cors');
app.use(cors({ origin: 'http://localhost:5173' }));

const express = require('express');
const mongoose = require('mongoose');
const dotenv = require('dotenv');
const bcrypt = require('bcrypt');
const Usuario = require('./models/Usuario');

dotenv.config();
const app = express();
app.use(express.json());

// Conexão com o MongoDB
mongoose.connect(process.env.MONGO_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true
}).then(() => console.log("🟢 MongoDB conectado"))
  .catch(err => console.error("🔴 Erro na conexão com MongoDB:", err));

// Rota de cadastro
app.post('/cadastro', async (req, res) => {
  const { nomeUsuario, nome, email, senha } = req.body;
  try {
    const senhaHash = await bcrypt.hash(senha, 10);
    const novoUsuario = new Usuario({ nomeUsuario, nome, email, senha: senhaHash });
    await novoUsuario.save();
    res.status(201).json({ mensagem: "Usuário cadastrado com sucesso" });
  } catch (err) {
    res.status(400).json({ erro: "Erro no cadastro", detalhes: err.message });
  }
});

// Rota de login
app.post('/login', async (req, res) => {
  const { email, senha } = req.body;
  try {
    const usuario = await Usuario.findOne({ email });
    if (!usuario) return res.status(404).json({ erro: "Usuário não encontrado" });

    const senhaValida = await bcrypt.compare(senha, usuario.senha);
    if (!senhaValida) return res.status(401).json({ erro: "Senha inválida" });

    res.json({ mensagem: "Login bem-sucedido", usuario: usuario.nomeUsuario });
  } catch (err) {
    res.status(500).json({ erro: "Erro no login", detalhes: err.message });
  }
});

// Inicia o servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`🚀 Servidor rodando na porta ${PORT}`);
});
