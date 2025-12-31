// License: The GitLab Enterprise Edition (EE) license (the “EE License”)
// Rule ref: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable

function test () {
  console.log('Running TestNodeLibCurl')
  testCurl1()
  testCurl2()
  testCurl3()
  testCurl4()
  testCurl5()
  testCurl6()
  testCurl7()
  testCurl8()
  testCurl9()
  testCurl10()
  testCurl11()
}

// Vulnerable Case
// Will connect with self-signed server
// Disable SSL certificate verification and hostname verification
// SSL_VERIFYPEER Disabled - Value set to 0
// SSL_VERIFYHOST Disabled - Value set to 0
function testCurl1 () {
  console.info(
    'Running Test One - Vulnerable - (Should connect with self-signed server)'
  )

  const { Curl } = require('node-libcurl')
  const curl = new Curl()
  // curl.setOpt('VERBOSE', 1)
  curl.setOpt('URL', 'https://myserver:8443')
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYPEER', 0)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYHOST', 0)

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test One - statusCode : ' + statusCode)
    console.info('Test One - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

// Vulnerable Case
// Will connect with self-signed server
// Disable SSL certificate verification and hostname verification
// SSL_VERIFYPEER Disabled - Value set to false
// SSL_VERIFYHOST Disabled - Value set to false
function testCurl2 () {
  console.info(
    'Running Test Two - Vulnerable - (Should connect with self-signed server)'
  )

  var Curl = require('node-libcurl')
  const curl = new Curl()
  // curl.setOpt('VERBOSE', 1)

  curl.setOpt('URL', 'https://myserver:8443')
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYPEER', false)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYHOST', false)

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Two - statusCode : ' + statusCode)
    console.info('Test Two - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

// Safe Case
// Will NOT connect with self-signed server
// Enable SSL certificate verification and hostname verification
// SSL_VERIFYPEER Enabled - Value set to 1 (Though by default it is enabled)
// SSL_VERIFYHOST Enabled - Value set to 2 (Though by default it is enabled)
function testCurl3 () {
  console.info('Running Test Three - Safe - No response would be returned')

  const { Curl } = require('node-libcurl')
  const curl = new Curl()
  // curl.setOpt('VERBOSE', 1)
  curl.setOpt('URL', 'https://myserver:8443')
  // ok: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYPEER', 1)
  // to enable SSL_VERIFYHOST, set the value to 2 instead of 1
  // ok: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYHOST', 2)

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Three - statusCode : ' + statusCode)
    console.info('Test Three - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

// Safe Case
// Will NOT connect with self-signed server
// Enable SSL certificate verification and hostname verification
// SSL_VERIFYPEER Enabled - Value set to true (Though by default it is enabled)
// SSL_VERIFYHOST Enabled - Value set to 2 (Though by default it is enabled)
function testCurl4 () {
  console.info('Running Test Four - Safe - No response would be returned')

  const { Curl } = require('node-libcurl')
  const curl = new Curl()
  // curl.setOpt('VERBOSE', 1)
  // ok: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYPEER', true)
  // ok: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYHOST', 2)
  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Four - statusCode : ' + statusCode)
    console.info('Test Four - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

/**  This is vulnerable to MITM, even though here it will not connect because of the self
 signed certificate being enabled. It can still be exploited in real life if attacker
 intercepts initial network traffic, and provides any CA certified certificate (hostname
 can be his own malicious domain). */
// SSL_VERIFYPEER Enabled - Value set to true
// SSL_VERIFYHOST Disabled - Value set to 0
function testCurl5 () {
  console.info('Running Test Five - Vulnerable - No response would be returned')

  const { Curl } = require('node-libcurl')
  const curl = new Curl()
  // curl.setOpt('VERBOSE', 1)
  // ok: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYPEER', true)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYHOST', 0)
  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Five - statusCode : ' + statusCode)
    console.info('Test Five - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

/** This is vulnerable to MITM, even though it will cause some issues
 * here because because the self-generated certificated has a different host name
 * than the one in the URL.
 * Enabling the Versbose mode will show the following error:
 * SSL: certificate subject name 'yourdomain.com' does not match target host name 'myserver'
 * */
// SSL_VERIFYPEER Disabled - Value set to 0
// SSL_VERIFYHOST Enabled - Value set to 2
function testCurl6 () {
  console.info('Running Test Six - Vulnerable - No response would be returned')

  const { Curl } = require('node-libcurl')
  const curl = new Curl()
  // curl.setOpt('VERBOSE', 1)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYPEER', 0)
  // ok: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt('SSL_VERIFYHOST', 2)
  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Six - statusCode : ' + statusCode)
    console.info('Test Six - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

function testCurl7 () {
  console.info('Running Test Six - Vulnerable - No response would be returned')

  const { Curl } = require('node-libcurl')
  const curl = new Curl()
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYHOST, 0)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYPEER, 0)

  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Six - statusCode : ' + statusCode)
    console.info('Test Six - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

import Curl from 'node-libcurl'
function testCurl8 () {
  console.info('Running Test Six - Vulnerable - No response would be returned')

  const curl = new Curl()
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYHOST, 0)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYPEER, 0)

  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Six - statusCode : ' + statusCode)
    console.info('Test Six - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

function testCurl9 () {
  console.info('Running Test Six - Vulnerable - No response would be returned')
  var Curl = require('node-libcurl')
  const curl = new Curl()
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYHOST, 0)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYPEER, 0)

  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Six - statusCode : ' + statusCode)
    console.info('Test Six - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

import { Curl } from 'node-libcurl'
function testCurl10 () {
  console.info('Running Test Six - Vulnerable - No response would be returned')

  const curl = new Curl()
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYHOST, 0)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curl.option.SSL_VERIFYPEER, 0)

  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Six - statusCode : ' + statusCode)
    console.info('Test Six - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

import { Curl as Curlalias } from 'node-libcurl'
function testCurl11 () {
  console.info('Running Test Six - Vulnerable - No response would be returned')

  const curl = new Curlalias()
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curlalias.option.SSL_VERIFYHOST, 0)
  // ruleid: javascript_crypto_rule-NodeLibcurlSSLVerificationDisable
  curl.setOpt(Curlalias.option.SSL_VERIFYPEER, 0)

  curl.setOpt('URL', 'https://myserver:8443')

  curl.on('end', function (statusCode, data, headers) {
    console.info('Test Six - statusCode : ' + statusCode)
    console.info('Test Six - response : ' + data)
    this.close()
  })

  curl.on('error', curl.close.bind(curl))
  curl.perform()
}

test()
