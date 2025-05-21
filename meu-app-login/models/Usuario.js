const mongoose = require('mongoose');

const usuarioSchema = new mongoose.Schema({
  nomeUsuario: { type: String, required: true, unique: true, minlength: 3 },
  nome:        { type: String, required: true, minlength: 3 },
  email:       { type: String, required: true, unique: true, match: /.+\@.+\..+/ },
  senha:       { type: String, required: true, minlength: 6 },
  criadoEm:    { type: Date, default: Date.now }
});

module.exports = mongoose.model('Usuario', usuarioSchema);
