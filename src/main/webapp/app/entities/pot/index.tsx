import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Pot from './pot';
import PotDetail from './pot-detail';
import PotUpdate from './pot-update';
import PotDeleteDialog from './pot-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PotDetail} />
      <ErrorBoundaryRoute path={match.url} component={Pot} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PotDeleteDialog} />
  </>
);

export default Routes;
