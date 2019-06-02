import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './table-structure.reducer';
import { ITableStructure } from 'app/shared/model/table-structure.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITableStructureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TableStructureDetail extends React.Component<ITableStructureDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tableStructureEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TableStructure [<b>{tableStructureEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bigBlind">Big Blind</span>
            </dt>
            <dd>{tableStructureEntity.bigBlind}</dd>
            <dt>
              <span id="rakePolicy">Rake Policy</span>
            </dt>
            <dd>{tableStructureEntity.rakePolicy}</dd>
            <dt>
              <span id="numberOfSeats">Number Of Seats</span>
            </dt>
            <dd>{tableStructureEntity.numberOfSeats}</dd>
            <dt>
              <span id="minBuyIn">Min Buy In</span>
            </dt>
            <dd>{tableStructureEntity.minBuyIn}</dd>
            <dt>
              <span id="maxBuyIn">Max Buy In</span>
            </dt>
            <dd>{tableStructureEntity.maxBuyIn}</dd>
          </dl>
          <Button tag={Link} to="/entity/table-structure" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/table-structure/${tableStructureEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ tableStructure }: IRootState) => ({
  tableStructureEntity: tableStructure.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TableStructureDetail);
