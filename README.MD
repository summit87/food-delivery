```textmate
Configuration file in local for OpenTelemetry
/etc/otelcol/config.yaml
/etc/otelcol/otelcol.conf

```



````YAML
#/etc/otelcol/config.yaml
# To limit exposure to denial of service attacks, change the host in endpoints below from 0.0.0.0 to a specific network interface.
# See https://github.com/open-telemetry/opentelemetry-collector/blob/main/docs/security-best-practices.md#safeguards-against-denial-of-service-attacks

extensions:
  health_check:
  pprof:
    endpoint: 0.0.0.0:1777
  zpages:
    endpoint: 0.0.0.0:55679

receivers:
  otlp:
    protocols:
      grpc:
        endpoint: 0.0.0.0:4317
      http:
        endpoint: 0.0.0.0:4318

  # opencensus:
  #   endpoint: 0.0.0.0:55678

  # # Collect own metrics
  # prometheus:
  #   config:
  #     scrape_configs:
  #     - job_name: 'otel-collector'
  #       scrape_interval: 10s
  #       static_configs:
  #       - targets: ['0.0.0.0:8888']

  # jaeger:
  #   protocols:
  #     grpc:
  #       endpoint: 0.0.0.0:14250
  #     thrift_binary:
  #       endpoint: 0.0.0.0:6832
  #     thrift_compact:
  #       endpoint: 0.0.0.0:6831
  #     thrift_http:
  #       endpoint: 0.0.0.0:14268

  # zipkin:
  #   endpoint: 0.0.0.0:9411

processors:
  batch:

exporters:
  logging:
    verbosity: detailed
  otlp:
    endpoint: otelcol:4317
  prometheus:
    endpoint: "0.0.0.0:8889"
    const_labels:
      label1: "Some value"
  zipkin:
    endpoint: "http://localhost:9411/api/v2/spans"
    format: proto

  # jaeger:
  #   endpoint: localhost:14250
  #   tls:
  #     insecure: true

service:

  pipelines:

    traces:
      receivers: [otlp]
      processors: [batch]
      exporters: [ zipkin]

    metrics:
      receivers: [otlp]
      processors: [batch]
      exporters: [prometheus]

  extensions: [health_check, pprof, zpages]
````

````conf
#/etc/otelcol/otelcol.conf
# Systemd environment file for the otelcol service

# Command-line options for the otelcol service.
# Run `/usr/bin/otelcol --help` to see all available options.
OTELCOL_OPTIONS="--config=/etc/otelcol/config.yaml"

````