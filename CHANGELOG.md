# Changelog

All notable changes to this project will be documented in this file.

The format is based on [keep a changelog](http://keepachangelog.com/en/1.0.0/) and using following
types of changes: `Added`, `Changed`, `Deprecated`, `Removed`, `Fixed`, `Security`, `Bumped`
and `Migration`.

This project adheres to [semantic versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased](https://github.com/wmontwe/mhp-mobile-challenge-android/releases/latest)

See [changeset](https://github.com/wmontwe/mhp-mobile-challenge-android/compare/v0.0.1...main)

### Added

- App navigation using Compose Navigation
- Compose test setup
- Atomic Design base
- House Domain contract and use case
- Data layer contract
- HttpClient using URLConnection
- IceAndFireApi
- RemoteDataSource
- Dependency injection via custom injector
- House list view
- GetHouseById use case
- House detail view

### Changed

- Identifier id to value to prevent id.id concatenation

### Fixed

- Fix pagination starting from 1 and not 0

## [0.0.1](https://github.com/wmontwe/mhp-mobile-challenge-android/releases/tag/v0.0.1)

Initial project configuration
